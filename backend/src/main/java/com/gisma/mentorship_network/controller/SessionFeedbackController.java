package com.gisma.mentorship_network.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gisma.mentorship_network.service.SessionFeedbackService;

@RestController
@RequestMapping("/api/session-feedbacks")
public class SessionFeedbackController {

    private final SessionFeedbackService sessionFeedbackService;

    public SessionFeedbackController(SessionFeedbackService sessionFeedbackService) {
        this.sessionFeedbackService = sessionFeedbackService;
    }
    
    @GetMapping   
    public List<SessionFeedbackService.SessionFeedbackDTO> getAllSessionFeedbacks() {
        return sessionFeedbackService.getAllSessionFeedbacks();
    }

    @GetMapping("/{id}")
    public SessionFeedbackService.SessionFeedbackDTO getSessionFeedbackById(@PathVariable Long id) {
        return sessionFeedbackService.getSessionFeedbackById(id);
    }

    @PostMapping
    public ResponseEntity<SessionFeedbackService.SessionFeedbackDTO> createSessionFeedback(@Valid @RequestBody SessionFeedbackService.CreateSessionFeedbackRequest request) {
        SessionFeedbackService.SessionFeedbackDTO createdFeedback = sessionFeedbackService.createSessionFeedback(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFeedback);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionFeedbackService.SessionFeedbackDTO>  updateSessionFeedback(@PathVariable Long id, @Valid @RequestBody SessionFeedbackService.UpdateSessionFeedbackRequest request) {
        SessionFeedbackService.SessionFeedbackDTO updatedFeedback = sessionFeedbackService.updateSessionFeedback(id, request);
        return ResponseEntity.ok(updatedFeedback);
    }

    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> deleteSessionFeedback(@PathVariable Long id) {
        sessionFeedbackService.deleteSessionFeedback(id);
        return ResponseEntity.noContent().build();
    }
    

}
