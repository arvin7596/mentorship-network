package com.gisma.mentorship_network.controller;

import com.gisma.mentorship_network.model.MentorAvailability;
import com.gisma.mentorship_network.model.WeekDay;
import com.gisma.mentorship_network.service.MentorAvailabilityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availabilities")
public class MentorAvailabilityController {
    private final MentorAvailabilityService mentorAvailabilityService;
    public MentorAvailabilityController(MentorAvailabilityService mentorAvailabilityService) {
        this.mentorAvailabilityService = mentorAvailabilityService;
    }

    @GetMapping
    public List<MentorAvailability> getAvailability() {
        return mentorAvailabilityService.getAllAvailability();
    }

    @GetMapping("/{mentorId}")
    public List<MentorAvailability> getAvailabilityByMentorId(@PathVariable Long mentorId) {
        return mentorAvailabilityService.getAvailabilityByMentorId(mentorId);
    }

    @GetMapping("/weekday/{weekday}")
    public List<MentorAvailability> getAvailabilityByWeekday(@PathVariable WeekDay weekday) {
        return mentorAvailabilityService.getAvailabilityByWeekday(weekday);
    }

    @GetMapping("/{mentorId}/time-slots")
    public List<MentorAvailabilityService.TimeSlot> getAvailableTimeSlots(@PathVariable Long mentorId) {
        return mentorAvailabilityService.getAvailableTimeSlots(mentorId);
    }

    @PostMapping("/{mentorId}")
    public List<MentorAvailability> createAvailability(@RequestBody List<MentorAvailabilityService.AvailabilityRequest> request, @PathVariable Long mentorId) {
        return mentorAvailabilityService.createAvailabilities(request, mentorId);
    }

    @PutMapping("/{id}")
    public MentorAvailability updateAvailability(@PathVariable Long id, @RequestBody MentorAvailabilityService.AvailabilityRequest availability) {
        return mentorAvailabilityService.updateAvailability(id, availability);
    }

    @DeleteMapping("/{id}")
    public void deleteAvailability(@PathVariable Long id) {
        mentorAvailabilityService.deleteAvailability(id);
    }
    
}
