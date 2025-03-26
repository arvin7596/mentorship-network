package com.gisma.mentorship_network.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gisma.mentorship_network.model.SessionFeedback;
import com.gisma.mentorship_network.service.SessionFeedbackService;

@RestController
@RequestMapping("/session-feedbacks")
public class SessionFeedbackController {

    private final SessionFeedbackService sessionFeedbackService;

    public SessionFeedbackController(SessionFeedbackService sessionFeedbackService) {
        this.sessionFeedbackService = sessionFeedbackService;
    }
    
    @GetMapping
    public List<SessionFeedback> getAllSessionFeedbacks() {
        return sessionFeedbackService.getAllSessionFeedbacks();
    }

    @GetMapping("/{id}")
    public SessionFeedback getSessionFeedbackById(@PathVariable Long id) {
        return sessionFeedbackService.getSessionFeedbackById(id);
    }

    @PostMapping
    public ResponseEntity<SessionFeedback> createSessionFeedback(@RequestBody SessionFeedbackService.CreateSessionFeedbackRequest request) {
        SessionFeedback createdFeedback = sessionFeedbackService.createSessionFeedback(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFeedback);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SessionFeedback>  updateSessionFeedback(@PathVariable Long id, @RequestBody SessionFeedbackService.UpdateSessionFeedbackRequest request) {
        SessionFeedback updatedFeedback = sessionFeedbackService.updateSessionFeedback(id, request);
        return ResponseEntity.ok(updatedFeedback);
    }

    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> deleteSessionFeedback(@PathVariable Long id) {
        sessionFeedbackService.deleteSessionFeedback(id);
        return ResponseEntity.noContent().build();
    }
    

}
