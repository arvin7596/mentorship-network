package com.gisma.mentorship_network.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gisma.mentorship_network.model.MentorshipSession;
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
        MentorshipSession session_id,   
        @NotBlank(message = "Mentee feedback is required")
        String mentee_feedback,
        @NotNull(message = "Rate is required")  
        double rate
    ){};
    public SessionFeedback createSessionFeedback(CreateSessionFeedbackRequest request) {
        SessionFeedback newFeedback = new SessionFeedback();
        newFeedback.setMenteeFeedback(request.mentee_feedback);
        newFeedback.setRate(request.rate);
        newFeedback.setSessionId(request.session_id);
        return sessionFeedbackRepository.save(newFeedback);
    }
    public record UpdateSessionFeedbackRequest(
        @NotBlank(message = "Mentee feedback is required")
        String mentee_feedback,
        @NotNull(message = "Rate is required")
        double rate
    ){};
    public SessionFeedback updateSessionFeedback(Long id, UpdateSessionFeedbackRequest request) {
        SessionFeedback existingFeedback = getSessionFeedbackById(id);
        existingFeedback.setMenteeFeedback(request.mentee_feedback);
        existingFeedback.setRate(request.rate);
        return sessionFeedbackRepository.save(existingFeedback);
    }   
    public void deleteSessionFeedback(Long id) {
        sessionFeedbackRepository.deleteById(id);
    }

}