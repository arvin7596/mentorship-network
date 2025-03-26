package com.gisma.mentorship_network.repository;

import com.gisma.mentorship_network.model.MentorshipSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorshipSessionRepository extends JpaRepository<MentorshipSession, Long> {
}
