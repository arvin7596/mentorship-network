package com.gisma.mentorship_network.controller;

import java.util.List;

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
    public SessionFeedback createSessionFeedback(@RequestBody SessionFeedbackService.CreateSessionFeedbackRequest request) {
        return sessionFeedbackService.createSessionFeedback(request);
    }
    
    

}
