package com.gisma.mentorship_network.repository;

import com.gisma.mentorship_network.model.Skill;

import com.gisma.mentorship_network.service.SkillService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SkillRepository
        extends JpaRepository<Skill, Long> {

    @Query("SELECT s FROM Skill s WHERE s.userId = :userId")
    List<Skill> getUserSkills(@Param("userId") Long userId);

    @Query("SELECT COUNT(s) > 0 FROM Skill s WHERE s.userId = :userId AND s.name = :name")
    boolean existsByUserIdAndName(@Param("userId") Long userId, @Param("name") String name);

    // @Query("SELECT DISTINCT u.id, u.first_name, u.last_name, u.email, u.country, u.city FROM User u JOIN Skill s ON u.id = s.userId WHERE s.name = :skillName AND u.is_mentor = true")
    // List<SkillService.MentorDetail> findMentorsWithSkill(@Param("skillName") String skillName);

    @Query("SELECT new com.gisma.mentorship_network.service.SkillService$MentorDetail(u.id, u.first_name, u.last_name, u.email, u.country, u.city) " +
       "FROM User u JOIN Skill s ON u.id = s.userId " +
       "WHERE s.name = :skillName AND u.is_mentor = true")
List<SkillService.MentorDetail> findMentorsWithSkill(@Param("skillName") String skillName);

}
