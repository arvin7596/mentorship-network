package com.gisma.mentorship_network.service;

import com.gisma.mentorship_network.model.MentorshipSession;
import com.gisma.mentorship_network.model.SessionStatus;
import com.gisma.mentorship_network.repository.MentorshipMatchRepository;
import com.gisma.mentorship_network.repository.MentorshipSessionRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.gisma.mentorship_network.service.MentorshipMatchService.getMentorshipMatchDTO;

@Service
public class MentorshipSessionService {
    private final MentorshipSessionRepository mentorshipSessionRepository;
    private final MentorshipMatchRepository mentorshipMatchRepository;
    public MentorshipSessionService(MentorshipSessionRepository mentorshipSessionRepository, MentorshipMatchRepository mentorshipMatchRepository) {
        this.mentorshipSessionRepository = mentorshipSessionRepository;
        this.mentorshipMatchRepository = mentorshipMatchRepository;
    }

    public record MentorshipSessionDTO(
        Long id,
        MentorshipMatchService.MentorshipMatchDTO mentorshipMatch,
        SessionStatus status,
        LocalDateTime scheduledDate,
        String mentorNotes,
        LocalDateTime createdAt
    ) {}

    public MentorshipSessionDTO getSessionDTO(MentorshipSession session) {
        return new MentorshipSessionDTO(
            session.getId(),
            getMentorshipMatchDTO(session.getMentorshipMatch()),
            session.getStatus(),
            session.getScheduledDate(),
            session.getMentorNotes(),
            session.getCreatedAt()
        );
    }

    public List<MentorshipSessionDTO> getAllMentorshipSessions() {
        return mentorshipSessionRepository.findAll().stream().map(this::getSessionDTO).toList();
    }

    public MentorshipSessionDTO getMentorshipSessionById(Long id) {
        if (!mentorshipSessionRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "MentorshipSession with ID " +  id + " not found.");
        }
        return getSessionDTO(mentorshipSessionRepository.findById(id).orElseThrow());
    }

    public record MentorshipSessionRequest(
            @NotNull(message = "Mentorship match is required")
            Long match_id,

            @NotNull(message = "Scheduled date is required")
            LocalDateTime scheduled_date
    ) {}

    public MentorshipSessionDTO createMentorshipSession(MentorshipSessionRequest request) {
        if (!mentorshipMatchRepository.existsById(request.match_id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "MentorshipMatch with ID " +  request.match_id + " not found.");
        }
        
        MentorshipSession newMentorshipSession = new MentorshipSession();
        newMentorshipSession.setScheduledDate(request.scheduled_date);
        newMentorshipSession.setMentorshipMatch(mentorshipMatchRepository.findById(request.match_id).orElseThrow());
        newMentorshipSession.setStatus(SessionStatus.PENDING);
        newMentorshipSession.setCreatedAt(LocalDateTime.now());
        MentorshipSession createdSession = mentorshipSessionRepository.save(newMentorshipSession);
        return getSessionDTO(createdSession);
    }

    public record UpdateMentorshipSessionRequest(
            LocalDateTime scheduled_date, SessionStatus status, String note
    ) {}

    public MentorshipSessionDTO updateMentorshipSession(Long id, UpdateMentorshipSessionRequest request) {
        if (!mentorshipSessionRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Session with ID " +  id + " not found.");
        }
        MentorshipSession existingSession = mentorshipSessionRepository.findById(id).orElseThrow();
        
        Optional.ofNullable(request.scheduled_date).ifPresent(existingSession::setScheduledDate);
        Optional.ofNullable(request.status).ifPresent(existingSession::setStatus);
        Optional.ofNullable(request.note).ifPresent(existingSession::setMentorNotes);
        
        MentorshipSession updatedSession = mentorshipSessionRepository.save(existingSession);
        return getSessionDTO(updatedSession);
    }

    public MentorshipSessionDTO updateMentorshipSessionStatus(Long id, SessionStatus status) {
        if (!mentorshipSessionRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Session with ID " +  id + " not found.");
        }
        MentorshipSession existingSession = mentorshipSessionRepository.findById(id).orElseThrow();
        existingSession.setStatus(status);
        MentorshipSession updatedSession = mentorshipSessionRepository.save(existingSession);
        return getSessionDTO(updatedSession);
    }

    public void deleteMentorshipSession(Long id) {
        if(!mentorshipSessionRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Session with ID " + id + " not found.");
        }

        mentorshipSessionRepository.deleteById(id);
    }
}
