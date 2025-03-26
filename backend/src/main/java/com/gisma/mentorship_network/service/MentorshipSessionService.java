package com.gisma.mentorship_network.service;

import com.gisma.mentorship_network.model.MentorshipSession;
import com.gisma.mentorship_network.repository.MentorshipSessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new EntityNotFoundException("Mentorship session not found"));
    }

    public MentorshipSession createMentorshipSession(MentorshipSession mentorshipSession) {
        return mentorshipSessionRepository.save(mentorshipSession);
    }

    public MentorshipSession updateMentorshipSession(Long id, MentorshipSession mentorshipSession) {
        MentorshipSession existingSession = getMentorshipSessionById(id);
        existingSession.setStatus(mentorshipSession.getStatus());
        existingSession.setScheduledDate(mentorshipSession.getScheduledDate());
        existingSession.setMentorNotes(mentorshipSession.getMentorNotes());
        return mentorshipSessionRepository.save(existingSession);
    }

    public void deleteMentorshipSession(Long id) {
        mentorshipSessionRepository.deleteById(id);
    }
}
