package com.gisma.mentorship_network.repository;

import com.gisma.mentorship_network.model.Skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SkillRepository
        extends JpaRepository<Skill, Integer> {

    @Query("SELECT s FROM Skill s WHERE s.user_id = :userId")
    List<Skill> getUserSkills(@Param("userId") Long userId);

    boolean existsByNameAndUser(String name, Long userId);
}

