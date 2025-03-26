package com.gisma.mentorship_network.repository;

import org.springframework.data.jpa.repository.JpaRepository;       
import com.gisma.mentorship_network.model.MentorshipMatch;

public interface MentorshipMatchRepository extends JpaRepository<MentorshipMatch, Long> {
    
    
}
