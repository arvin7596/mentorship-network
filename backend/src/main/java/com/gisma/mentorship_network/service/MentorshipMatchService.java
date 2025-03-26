package com.gisma.mentorship_network.service;

import com.gisma.mentorship_network.model.MentorshipMatch;
import com.gisma.mentorship_network.repository.MentorshipMatchRepository;
import com.gisma.mentorship_network.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
         Long mentor_id,
         @NotNull(message = "Mentee ID is required")
         Long mentee_id,
         @NotBlank(message = "Topic is required")
         String topic
     ){
     };
     public MentorshipMatch createMentorshipMatch(CreateMentorshipMatchRequest request) {
         if (!userRepository.existsById(request.mentor_id())) {
             throw new ResponseStatusException(
                     HttpStatus.NOT_FOUND, "Mentor with ID " +  request.mentor_id + " not found.");
         }
         if (!userRepository.existsById(request.mentee_id())) {
             throw new ResponseStatusException(
                     HttpStatus.NOT_FOUND, "Mentee with ID " +  request.mentee_id + " not found.");
         }
         MentorshipMatch mentorshipMatch = new MentorshipMatch();
         mentorshipMatch.setMentor_id(request.mentor_id());
         mentorshipMatch.setMentee_id(request.mentee_id());
         mentorshipMatch.setTopic(request.topic());
         return mentorshipMatchRepository.save(mentorshipMatch);
     }

     public MentorshipMatch updateMentorshipMatch(Long id, MentorshipMatch mentorshipMatch) {
         if (!mentorshipMatchRepository.existsById(id)) {
             throw new RuntimeException("MentorshipMatch not found with id: " + id);
         }
         mentorshipMatch.setId(id);
         return mentorshipMatchRepository.save(mentorshipMatch);
     }

     public void deleteMentorshipMatch(Long id) {
         mentorshipMatchRepository.deleteById(id);
     }

}
