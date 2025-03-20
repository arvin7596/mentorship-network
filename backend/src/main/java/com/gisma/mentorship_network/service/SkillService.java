package com.gisma.mentorship_network.service;

import com.gisma.mentorship_network.model.Skill;
import com.gisma.mentorship_network.model.SkillLevel;
import com.gisma.mentorship_network.repository.SkillRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SkillService {
    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Skill> getAllUserSkills(Long userId) {
        return skillRepository.getUserSkills(userId);
    }

    public record CreateSkillRequest(String skill_name, Long user_id, SkillLevel skill_level) {}
    public Skill createUserSkill(CreateSkillRequest request) {
        if (skillRepository.existsByNameAndUser(request.skill_name, request.user_id)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Skill already existed");
        }

        Skill skill = new Skill();
        skill.setSkill_name(request.skill_name);
        skill.setSkill_level(request.skill_level);
        skill.setUser_id(request.user_id);
        return skillRepository.save(skill);
    }
}
