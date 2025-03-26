package com.gisma.mentorship_network.service;

import com.gisma.mentorship_network.model.MatchStatus;
import com.gisma.mentorship_network.model.MentorshipMatch;
import com.gisma.mentorship_network.model.User;
import com.gisma.mentorship_network.repository.MentorshipMatchRepository;
import com.gisma.mentorship_network.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

     public List<MentorshipMatch> getAllMentorshipMatches() {
         return mentorshipMatchRepository.findAll();
     }

     public MentorshipMatch getMentorshipMatchById(Long id) {
        if (!mentorshipMatchRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "MentorshipMatch with ID " +  id + " not found.");
        }
        return mentorshipMatchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MentorshipMatch not found with id: " + id));
     }

     public record CreateMentorshipMatchRequest(
         @NotNull(message = "Mentor ID is required")
         User mentor,
         @NotNull(message = "Mentee ID is required")
         User mentee,
         @NotBlank(message = "Topic is required")
         String topic
     ){
     };
     public MentorshipMatch createMentorshipMatch(CreateMentorshipMatchRequest request) {

         if (!userRepository.existsById(request.mentor.getId())) {
             throw new ResponseStatusException(
                     HttpStatus.NOT_FOUND, "Mentor with ID " +  request.mentor.getId() + " not found.");
         }
         if (!userRepository.existsById(request.mentee.getId())) {
             throw new ResponseStatusException(
                     HttpStatus.NOT_FOUND, "Mentee with ID " +  request.mentee.getId() + " not found.");
         }
         MentorshipMatch mentorshipMatch = new MentorshipMatch();
         mentorshipMatch.setMentor(request.mentor);
         mentorshipMatch.setMentee(request.mentee);
         mentorshipMatch.setTopic(request.topic);
         mentorshipMatch.setStatus(MatchStatus.NEW);
         mentorshipMatch.setProgress(0);
         mentorshipMatch.setMentorFeedback("");
         mentorshipMatch.setMenteeFeedback("");
         return mentorshipMatchRepository.save(mentorshipMatch);
     }

     public MentorshipMatch updateMentorshipMatch(Long id, MentorshipMatch mentorshipMatch) {
         if (!mentorshipMatchRepository.existsById(id)) {
             throw new RuntimeException("MentorshipMatch not found with id: " + id);
         }
         MentorshipMatch existingMatch = getMentorshipMatchById(id);
         Optional.ofNullable(mentorshipMatch.getStatus()).ifPresent(existingMatch::setStatus);
         Optional.ofNullable(mentorshipMatch.getProgress()).ifPresent(existingMatch::setProgress);
         Optional.ofNullable(mentorshipMatch.getMentorFeedback()).ifPresent(existingMatch::setMentorFeedback);
         Optional.ofNullable(mentorshipMatch.getMenteeFeedback()).ifPresent(existingMatch::setMenteeFeedback);
         mentorshipMatch.setId(id);
         return mentorshipMatchRepository.save(mentorshipMatch);
     }

     public void deleteMentorshipMatch(Long id) {
         mentorshipMatchRepository.deleteById(id);
     }

}
