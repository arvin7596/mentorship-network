package com.gisma.mentorship_network.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gisma.mentorship_network.model.SessionFeedback;

public interface SessionFeedbackRepository extends JpaRepository<SessionFeedback, Long> {
    
}
