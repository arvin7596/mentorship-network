package com.gisma.mentorship_network.service;

import com.gisma.mentorship_network.model.MatchStatus;
import com.gisma.mentorship_network.model.MentorshipMatch;
import com.gisma.mentorship_network.model.User;
import com.gisma.mentorship_network.model.UserDTO;
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
    public record MentorshipMatchDTO(
        Long id, UserDTO mentor, UserDTO mentee, String topic, MatchStatus status, int progress, String mentorFeedback, String menteeFeedback
        ){}

    //  public List<MentorshipMatchDTO> getAllMentorshipMatches() {
    //      List<MentorshipMatch> allMatches = mentorshipMatchRepository.findAll();
    //      return new MentorshipMatchDTO()
    //  }
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

     public MentorshipMatchDTO createMentorshipMatch(CreateMentorshipMatchRequest request) {
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
         MentorshipMatch createdMatch = mentorshipMatchRepository.save(mentorshipMatch);
         UserDTO mentorDTO = new UserDTO(createdMatch.getMentor().getId(), createdMatch.getMentor().getFirst_name(), createdMatch.getMentor().getLast_name(), createdMatch.getMentor().getEmail());
         UserDTO menteeDTO = new UserDTO(createdMatch.getMentee().getId(), createdMatch.getMentee().getFirst_name(), createdMatch.getMentee().getLast_name(), createdMatch.getMentee().getEmail());
         return new MentorshipMatchDTO(createdMatch.getId(), mentorDTO, menteeDTO, createdMatch.getTopic(), createdMatch.getStatus(), createdMatch.getProgress(), createdMatch.getMentorFeedback(), createdMatch.getMenteeFeedback());
     }

     public MentorshipMatchDTO updateMentorshipMatch(Long id, MentorshipMatch mentorshipMatch) {
         if (!mentorshipMatchRepository.existsById(id)) {
             throw new RuntimeException("MentorshipMatch not found with id: " + id);
         }
         MentorshipMatch existingMatch = getMentorshipMatchById(id);
         Optional.ofNullable(mentorshipMatch.getStatus()).ifPresent(existingMatch::setStatus);
         Optional.ofNullable(mentorshipMatch.getProgress()).ifPresent(existingMatch::setProgress);
         Optional.ofNullable(mentorshipMatch.getMentorFeedback()).ifPresent(existingMatch::setMentorFeedback);
         Optional.ofNullable(mentorshipMatch.getMenteeFeedback()).ifPresent(existingMatch::setMenteeFeedback);
         mentorshipMatch.setId(id);
         MentorshipMatch updatedMatch = mentorshipMatchRepository.save(mentorshipMatch);
         UserDTO mentorDTO = new UserDTO(updatedMatch.getMentor().getId(), updatedMatch.getMentor().getFirst_name(), updatedMatch.getMentor().getLast_name(), updatedMatch.getMentor().getEmail());
         UserDTO menteeDTO = new UserDTO(updatedMatch.getMentee().getId(), updatedMatch.getMentee().getFirst_name(), updatedMatch.getMentee().getLast_name(), updatedMatch.getMentee().getEmail());
         return new MentorshipMatchDTO(updatedMatch.getId(), mentorDTO, menteeDTO, updatedMatch.getTopic(), updatedMatch.getStatus(), updatedMatch.getProgress(), updatedMatch.getMentorFeedback(), updatedMatch.getMenteeFeedback());
     }

     public void deleteMentorshipMatch(Long id) {
         mentorshipMatchRepository.deleteById(id);
     }

}
