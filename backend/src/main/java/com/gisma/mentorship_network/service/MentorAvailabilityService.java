package com.gisma.mentorship_network.service;

import com.gisma.mentorship_network.model.MentorAvailability;
import com.gisma.mentorship_network.model.User;
import com.gisma.mentorship_network.model.WeekDay;
import com.gisma.mentorship_network.repository.MentorAvailabilityRepository;
import com.gisma.mentorship_network.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import java.util.UUID;

@Service
public class MentorAvailabilityService {
    private final MentorAvailabilityRepository mentorAvailabilityRepository;
    private final UserRepository userRepository;

    public MentorAvailabilityService(MentorAvailabilityRepository mentorAvailabilityRepository, UserRepository userRepository) {
        this.mentorAvailabilityRepository = mentorAvailabilityRepository;
        this.userRepository = userRepository;
    }

    public record AvailabilityDTO(
            Long id,
            LocalTime startTime,
            LocalTime endTime,
            WeekDay weekday,
            UserService.UserDTO mentor
    ) {}

    // Convert MentorAvailability to AvailabilityDTO
    AvailabilityDTO getAvailabilityDTO(MentorAvailability mentorAvailability) {
        return new AvailabilityDTO(mentorAvailability.getId(), mentorAvailability.getStartTime(), mentorAvailability.getEndTime(), mentorAvailability.getWeekday(), UserService.getUserDTO(mentorAvailability.getMentor()));
    }

    public List<AvailabilityDTO> getAllAvailability() {
        return mentorAvailabilityRepository.findAll().stream().map(this::getAvailabilityDTO).toList();
    }

    public List<AvailabilityDTO> getAvailabilityByMentorId(Long mentorId) {
        return mentorAvailabilityRepository.findByMentorId(mentorId).stream().map(this::getAvailabilityDTO).toList();
    }

    public List<AvailabilityDTO> getAvailabilityByWeekday(WeekDay weekday) {
        return mentorAvailabilityRepository.findByWeekday(weekday).stream().map(this::getAvailabilityDTO).toList();
    }

    // TimeSlot class to represent a time slot with a unique identifier, weekday, start time, and end time
    public record TimeSlot(
        String uuid,
        WeekDay weekday,
        LocalTime from,
        LocalTime to
    ) {}

    public List<TimeSlot> getAvailableTimeSlots(Long mentorId) {
        List<MentorAvailability> mentorAvailabilities = mentorAvailabilityRepository.findByMentorId(mentorId);
        List<TimeSlot> availableSlots = new ArrayList<>();

        for (MentorAvailability availability : mentorAvailabilities) {
            LocalTime currentTime = availability.getStartTime();
            
            while (currentTime.isBefore(availability.getEndTime())) {
                LocalTime slotEnd = currentTime.plusMinutes(30);
                
                // Don't create partial slots at the end
                if (slotEnd.isAfter(availability.getEndTime())) {
                    break;
                }

                availableSlots.add(new TimeSlot(
                    UUID.randomUUID().toString(),
                    availability.getWeekday(),
                    currentTime,
                    slotEnd
                ));

                currentTime = slotEnd;
            }
        }

        return availableSlots;
    }

    private void checkForTimeOverlap(AvailabilityRequest newRequest, MentorAvailability existing) {
        if (existing.getWeekday() == newRequest.weekday()) {
            if (isTimeOverlapping(
                    newRequest.start_time,
                    newRequest.end_time,
                    existing.getStartTime(),
                    existing.getEndTime())) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "New availability overlaps with existing slot on " +
                    existing.getWeekday() + " between " +
                    existing.getStartTime() + " and " +
                    existing.getEndTime()
                );
            }
        }
    }

    private boolean isTimeOverlapping(LocalTime newStart, LocalTime newEnd, LocalTime existingStart, LocalTime existingEnd) {
        return !(newEnd.isBefore(existingStart) || newStart.isAfter(existingEnd));
    }


    public record AvailabilityRequest(
        @NotBlank(message = "Start time is required")
        LocalTime start_time,

        @NotBlank(message = "End time is required")
        LocalTime end_time,

        @NotBlank(message = "Weekday is required")
        WeekDay weekday
        ) {}

    public List<AvailabilityDTO> createAvailabilities(List<AvailabilityRequest> request, Long mentorId) {
        // Get existing availabilities for this mentor
        List<MentorAvailability> existingAvailabilities = mentorAvailabilityRepository.findByMentorId(mentorId);
        // Check for possible time overlaps
        for (AvailabilityRequest newRequest : request) {
            for (MentorAvailability existing : existingAvailabilities) {
                checkForTimeOverlap(newRequest, existing);
            }
        }

        for (AvailabilityRequest availabilityRequest : request) {
            MentorAvailability availability = new MentorAvailability();
            User mentor = userRepository.findById(mentorId)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Mentor with ID " + mentorId + " not found"));
            availability.setMentor(mentor);
            availability.setStartTime(availabilityRequest.start_time());
            availability.setEndTime(availabilityRequest.end_time());
            availability.setWeekday(availabilityRequest.weekday());
            mentorAvailabilityRepository.save(availability);
        }
        List<MentorAvailability> mentorAvailabilities = mentorAvailabilityRepository.findByMentorId(mentorId);

        return mentorAvailabilities.stream().map(this::getAvailabilityDTO).toList();
    }


    public AvailabilityDTO updateAvailability(Long id, AvailabilityRequest request) {
        MentorAvailability existingAvailability = mentorAvailabilityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Availability not found"));

        // Get existing availabilities for this mentor
        List<MentorAvailability> existingAvailabilities = mentorAvailabilityRepository.findByMentorId(existingAvailability.getMentor().getId());

        for (MentorAvailability existing : existingAvailabilities) {
            // Skip checking against the record being updated
            if (existing.getId().equals(id)) {
                continue;
            }
            checkForTimeOverlap(request, existing);
        }

        existingAvailability.setStartTime(request.start_time);
        existingAvailability.setEndTime(request.end_time);
        existingAvailability.setWeekday(request.weekday);
        MentorAvailability updatedAvailability = mentorAvailabilityRepository.save(existingAvailability);
        return getAvailabilityDTO(updatedAvailability);
    }

    public void deleteAvailability(Long id) {
        if(!mentorAvailabilityRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Availability with ID " + id + " not found.");
        }
        mentorAvailabilityRepository.deleteById(id);
    }
}
