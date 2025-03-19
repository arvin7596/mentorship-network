package com.gisma.mentorship_network.service;

import com.gisma.mentorship_network.model.Skill;
import com.gisma.mentorship_network.repository.SkillRepository;
import org.springframework.stereotype.Service;

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
}
