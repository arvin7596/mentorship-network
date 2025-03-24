package com.gisma.mentorship_network.controller;

import com.gisma.mentorship_network.model.Skill;
import com.gisma.mentorship_network.service.SkillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<Skill> getAllUserSkills(@PathVariable Long userId) {
        return skillService.getAllUserSkills(userId);
    }

    @PostMapping
    public Skill createSkill(@RequestBody SkillService.CreateSkillRequest request) {
        return skillService.createUserSkill(request);
    }   


}
