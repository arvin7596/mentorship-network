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
            Long match_id,

            @NotBlank(message = "Scheduled date is required")
            LocalDateTime scheduled_date
            ) {}

    public MentorshipSession createMentorshipSession(MentorshipSessionRequest request) {
        MentorshipSession newMentorshipSession = new MentorshipSession();
        newMentorshipSession.setScheduledDate(request.scheduled_date);
        newMentorshipSession.setMentorshipMatchId(request.match_id);
        newMentorshipSession.setStatus(SessionStatus.PENDING);
        newMentorshipSession.setCreatedAt(LocalDateTime.now());
        return mentorshipSessionRepository.save(newMentorshipSession);
    }

    public record UpdateMentorshipSessionRequest(
            LocalDateTime scheduled_date, SessionStatus status, String note
    ) {}

    public MentorshipSession updateMentorshipSession(Long id, UpdateMentorshipSessionRequest request) {
        MentorshipSession existingSession = getMentorshipSessionById(id);
        
        if (request.status != null) {
            existingSession.setStatus(request.status);
        }
        
        if (request.scheduled_date != null) {
            existingSession.setScheduledDate(request.scheduled_date);
        }
        
        if (request.note != null) {
            existingSession.setMentorNotes(request.note);
        }
        
        return mentorshipSessionRepository.save(existingSession);
    }

    public MentorshipSession updateMentorshipSessionStatus(Long id, SessionStatus status) {
        MentorshipSession existingSession = getMentorshipSessionById(id);
        existingSession.setStatus(status);
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
