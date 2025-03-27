package com.gisma.mentorship_network.service;

import java.time.LocalDateTime;
import java.util.List;

import com.gisma.mentorship_network.repository.MentorshipSessionRepository;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.gisma.mentorship_network.model.SessionFeedback;
import com.gisma.mentorship_network.repository.SessionFeedbackRepository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Service
public class SessionFeedbackService {
    private final SessionFeedbackRepository sessionFeedbackRepository;
    private final MentorshipSessionRepository mentorshipSessionRepository;

    public SessionFeedbackService(SessionFeedbackRepository sessionFeedbackRepository, MentorshipSessionRepository mentorshipSessionRepository) {
        this.sessionFeedbackRepository = sessionFeedbackRepository;
        this.mentorshipSessionRepository = mentorshipSessionRepository;
    }

    public record SessionFeedbackDTO(
        Long id,
        MentorshipSessionService.MentorshipSessionDTO session,
        String menteeFeedback,
        double rate,
        LocalDateTime createdAt
    ){}

    public static SessionFeedbackDTO getSessionFeedbackDTO(SessionFeedback sessionFeedback) {
        return new SessionFeedbackDTO(
                sessionFeedback.getId(),
                MentorshipSessionService.getSessionDTO(sessionFeedback.getSession()),
                sessionFeedback.getMenteeFeedback(),
                sessionFeedback.getRate(),
                sessionFeedback.getCreatedAt()
        );
    }

    public List<SessionFeedbackDTO> getAllSessionFeedbacks() {
        return sessionFeedbackRepository.findAll().stream().map(SessionFeedbackService::getSessionFeedbackDTO).toList();
    }
    
    public SessionFeedbackDTO getSessionFeedbackById(Long id) {
        return getSessionFeedbackDTO(sessionFeedbackRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Session feedback not found")));
    }
    public record CreateSessionFeedbackRequest(
        @NotNull(message = "Session ID is required")
        Long session_id,

        @NotBlank(message = "Mentee feedback is required")
        String mentee_feedback,

        @NotNull(message = "Rate is required")  
        @Min(value = 1, message = "Rate must be at least 1")
        @Max(value = 10, message = "Rate must be at most 10")
        double rate
    ){};
    
    public SessionFeedbackDTO createSessionFeedback(CreateSessionFeedbackRequest request) {
        if (!mentorshipSessionRepository.existsById(request.session_id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Session not found");
        }
        SessionFeedback newFeedback = new SessionFeedback();
        newFeedback.setMenteeFeedback(request.mentee_feedback);
        newFeedback.setRate(request.rate);
        newFeedback.setSession(mentorshipSessionRepository.findById(request.session_id).orElseThrow());
        return getSessionFeedbackDTO(sessionFeedbackRepository.save(newFeedback));
    }

    public record UpdateSessionFeedbackRequest(
        @NotBlank(message = "Mentee feedback is required")
        String mentee_feedback,

        @NotNull(message = "Rate is required")
        @Min(value = 1, message = "Rate must be at least 1")
        @Max(value = 10, message = "Rate must be at most 10")
        double rate
    ){};

    public SessionFeedbackDTO updateSessionFeedback(Long id, UpdateSessionFeedbackRequest request) {
        if (!sessionFeedbackRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Session feedback not found");
        }
        SessionFeedback existingFeedback = sessionFeedbackRepository.findById(id).orElseThrow();
        existingFeedback.setMenteeFeedback(request.mentee_feedback);
        existingFeedback.setRate(request.rate);
        return getSessionFeedbackDTO(sessionFeedbackRepository.save(existingFeedback));
    }   
    public void deleteSessionFeedback(Long id) {
        if (!sessionFeedbackRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Session feedback not found");
        }
        sessionFeedbackRepository.deleteById(id);
    }

}