package com.gisma.mentorship_network.service;

import com.gisma.mentorship_network.model.MentorshipSession;
import com.gisma.mentorship_network.model.SessionStatus;
import com.gisma.mentorship_network.repository.MentorshipSessionRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MentorshipSessionService {
    private final MentorshipSessionRepository mentorshipSessionRepository;

    public MentorshipSessionService(MentorshipSessionRepository mentorshipSessionRepository) {
        this.mentorshipSessionRepository = mentorshipSessionRepository;
    }

    public List<MentorshipSession> getAllMentorshipSessions() {
        return mentorshipSessionRepository.findAll();
    }

    public MentorshipSession getMentorshipSessionById(Long id) {
        return mentorshipSessionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Mentorship session not found"));
    }

    public record MentorshipSessionRequest(
            @NotBlank(message = "Mentorship match is required")
            Long mentorshipMatchId,

            @NotBlank(message = "Scheduled date is required")
            LocalDateTime scheduledDate
            ) {}

    public MentorshipSession createMentorshipSession(MentorshipSessionRequest request) {
        MentorshipSession newMentorshipSession = new MentorshipSession();
        newMentorshipSession.setScheduledDate(request.scheduledDate);
        newMentorshipSession.setMentorshipMatchId(request.mentorshipMatchId);
        return mentorshipSessionRepository.save(newMentorshipSession);
    }

    public record UpdateMentorshipSessionRequest(
            LocalDateTime scheduledDate, SessionStatus status, String mentorNotes
    ) {}

    public MentorshipSession updateMentorshipSession(Long id, UpdateMentorshipSessionRequest request) {
        MentorshipSession existingSession = getMentorshipSessionById(id);
        existingSession.setStatus(request.status);
        existingSession.setScheduledDate(request.scheduledDate);
        existingSession.setMentorNotes(request.mentorNotes);
        return mentorshipSessionRepository.save(existingSession);
    }

    public void deleteMentorshipSession(Long id) {
        if(!mentorshipSessionRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Session with ID " + id + " not found.");
        }

        mentorshipSessionRepository.deleteById(id);
    }
}
