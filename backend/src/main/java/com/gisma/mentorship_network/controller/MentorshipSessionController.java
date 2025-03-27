package com.gisma.mentorship_network.controller;

import com.gisma.mentorship_network.model.MentorshipSession;
import com.gisma.mentorship_network.model.SessionStatus;
import com.gisma.mentorship_network.service.MentorshipSessionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class MentorshipSessionController {
    private final MentorshipSessionService mentorshipSessionService;

    public MentorshipSessionController(MentorshipSessionService mentorshipSessionService) {
        this.mentorshipSessionService = mentorshipSessionService;
    }

    @GetMapping
    public List<MentorshipSessionService.MentorshipSessionDTO> getMentorshipSessions() {
        return mentorshipSessionService.getAllMentorshipSessions();
    }

    @GetMapping("/{id}")
    public MentorshipSessionService.MentorshipSessionDTO getMentorshipSession(@PathVariable Long id) {
        return mentorshipSessionService.getMentorshipSessionById(id);
    }

    @PostMapping
    public MentorshipSessionService.MentorshipSessionDTO createMentorshipSession(@Valid @RequestBody MentorshipSessionService.MentorshipSessionRequest mentorshipSession) {
        return mentorshipSessionService.createMentorshipSession(mentorshipSession);
    }

    @PutMapping("/{id}")
    public MentorshipSessionService.MentorshipSessionDTO updateMentorshipSession(@PathVariable Long id, @Valid @RequestBody MentorshipSessionService.UpdateMentorshipSessionRequest mentorshipSession) {
        return mentorshipSessionService.updateMentorshipSession(id, mentorshipSession);
    }

    @PutMapping("/{id}/status")
    public MentorshipSessionService.MentorshipSessionDTO updateMentorshipSessionStatus(@PathVariable Long id,@Valid @RequestBody SessionStatus status) {
        return mentorshipSessionService.updateMentorshipSessionStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteMentorshipSession(@PathVariable Long id) {
        mentorshipSessionService.deleteMentorshipSession(id);
    }
}
