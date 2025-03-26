package com.gisma.mentorship_network.service;

import java.time.LocalDateTime;
import java.util.List;

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

    public SessionFeedbackService(SessionFeedbackRepository sessionFeedbackRepository) {
        this.sessionFeedbackRepository = sessionFeedbackRepository;
    }

    public List<SessionFeedback> getAllSessionFeedbacks() {
        return sessionFeedbackRepository.findAll();
    }
    
    public SessionFeedback getSessionFeedbackById(Long id) {
        return sessionFeedbackRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Session feedback not found"));
    }
    public record CreateSessionFeedbackRequest(
        @NotNull(message = "Session ID is required")
        Long sessionId,
        @NotBlank(message = "Mentee feedback is required")
        String menteeFeedback,
        @NotNull(message = "Rate is required")  
        double rate
    ){};
    public SessionFeedback createSessionFeedback(CreateSessionFeedbackRequest request) {
        return sessionFeedbackRepository.save(new SessionFeedback(null, request.sessionId(), request.menteeFeedback(), request.rate(), LocalDateTime.now()));
    }
    public record UpdateSessionFeedbackRequest(
        @NotBlank(message = "Mentee feedback is required")
        String menteeFeedback,
        @NotNull(message = "Rate is required")
        double rate
    ){};
    public SessionFeedback updateSessionFeedback(Long id, UpdateSessionFeedbackRequest request) {
        SessionFeedback existingFeedback = getSessionFeedbackById(id);
        existingFeedback.setMenteeFeedback(request.menteeFeedback());
        existingFeedback.setRate(request.rate());
        return sessionFeedbackRepository.save(existingFeedback);
    }   
    public void deleteSessionFeedback(Long id) {
        sessionFeedbackRepository.deleteById(id);
    }

}