package com.gisma.mentorship_network.service;

import com.gisma.mentorship_network.model.MatchStatus;
import com.gisma.mentorship_network.model.MentorshipMatch;
import com.gisma.mentorship_network.model.UserDTO;
import com.gisma.mentorship_network.repository.MentorshipMatchRepository;
import com.gisma.mentorship_network.repository.UserRepository;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Service
public class MentorshipMatchService {
    private final MentorshipMatchRepository mentorshipMatchRepository;
    private final UserRepository userRepository;

    public MentorshipMatchService(MentorshipMatchRepository mentorshipMatchRepository , UserRepository userRepository) {
        this.mentorshipMatchRepository = mentorshipMatchRepository;
        this.userRepository = userRepository;

    }

    public record MentorshipMatchDTO(
            Long id,
            UserDTO mentor,
            UserDTO mentee,
            String topic,
            MatchStatus status,
            int progress,
            String mentorFeedback,
            String menteeFeedback
    ){}

    public static MentorshipMatchDTO getMentorshipMatchDTO(MentorshipMatch mentorshipMatch) {
        return new MentorshipMatchDTO(mentorshipMatch.getId(), UserService.getUserDTO(mentorshipMatch.getMentor()), UserService.getUserDTO(mentorshipMatch.getMentee()), mentorshipMatch.getTopic(), mentorshipMatch.getStatus(), mentorshipMatch.getProgress(), mentorshipMatch.getMentorFeedback(), mentorshipMatch.getMenteeFeedback());
    }

    public List<MentorshipMatchDTO> getAllMentorshipMatches() {
        return mentorshipMatchRepository.findAll().stream().map(MentorshipMatchService::getMentorshipMatchDTO).toList();
     }

     public MentorshipMatchDTO getMentorshipMatchById(Long id) {
        if (!mentorshipMatchRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "MentorshipMatch with ID " +  id + " not found.");
        }
        return getMentorshipMatchDTO(mentorshipMatchRepository.findById(id).orElseThrow());
     }

     public List<MentorshipMatchDTO> getMentorshipMatchesByMentorId(Long mentorId) {
        return mentorshipMatchRepository.findByMentorId(mentorId).stream().map(MentorshipMatchService::getMentorshipMatchDTO).toList();
     }

     public record CreateMentorshipMatchRequest(
         @NotNull(message = "Mentor ID is required")
         Long mentor_id,
         @NotNull(message = "Mentee ID is required")
         Long mentee_id,
         @NotBlank(message = "Topic is required")
         String topic
     ) {};

     public MentorshipMatchDTO createMentorshipMatch(CreateMentorshipMatchRequest request) {
         if (!userRepository.existsById(request.mentor_id)) {
             throw new ResponseStatusException(
                     HttpStatus.NOT_FOUND, "Mentor with ID " +  request.mentor_id + " not found.");
         }
         if (!userRepository.existsById(request.mentee_id)) {
             throw new ResponseStatusException(
                     HttpStatus.NOT_FOUND, "Mentee with ID " +  request.mentee_id + " not found.");
         }
         MentorshipMatch mentorshipMatch = new MentorshipMatch();
         mentorshipMatch.setMentor(userRepository.findById(request.mentor_id).orElseThrow());
         mentorshipMatch.setMentee(userRepository.findById(request.mentee_id).orElseThrow());
         mentorshipMatch.setTopic(request.topic);
         mentorshipMatch.setStatus(MatchStatus.NEW);
         mentorshipMatch.setProgress(0);

         MentorshipMatch createdMatch = mentorshipMatchRepository.save(mentorshipMatch);
         return getMentorshipMatchDTO(createdMatch);
     }

     public record UpdateMentorshipMatchRequest(
         @Size(min = 3, max = 100, message = "Topic must be between 3 and 50 characters")
         String topic,
         
         MatchStatus status,
         @Min(value = 0, message = "Progress must be at least 0")
         @Max(value = 100, message = "Progress must be at most 100")
         int progress,
         String mentor_feedback,
         String mentee_feedback
     ) {};

     public MentorshipMatchDTO updateMentorshipMatch(Long id, UpdateMentorshipMatchRequest mentorshipMatch) {
         if (!mentorshipMatchRepository.existsById(id)) {
             throw new ResponseStatusException(
                     HttpStatus.NOT_FOUND, "MentorshipMatch with ID " +  id + " not found.");
         }
         MentorshipMatch existingMatch = mentorshipMatchRepository.findById(id).orElseThrow();
         Optional.ofNullable(mentorshipMatch.status).ifPresent(existingMatch::setStatus);
         Optional.ofNullable(mentorshipMatch.progress).ifPresent(existingMatch::setProgress);
         Optional.ofNullable(mentorshipMatch.mentor_feedback).ifPresent(existingMatch::setMentorFeedback);
         Optional.ofNullable(mentorshipMatch.mentee_feedback).ifPresent(existingMatch::setMenteeFeedback);
         MentorshipMatch updatedMatch = mentorshipMatchRepository.save(existingMatch);
         return getMentorshipMatchDTO(updatedMatch);
     }

     public void deleteMentorshipMatch(Long id) {
        if (!mentorshipMatchRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "MentorshipMatch with ID " +  id + " not found.");
        }
        mentorshipMatchRepository.deleteById(id);
     }

}
