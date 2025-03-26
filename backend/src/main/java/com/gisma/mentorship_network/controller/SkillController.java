package com.gisma.mentorship_network.controller;

import com.gisma.mentorship_network.model.Skill;
import com.gisma.mentorship_network.service.SkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/{userId}")
    public List<Skill> getAllUserSkills(@PathVariable Long userId) {
        return skillService.getAllUserSkills(userId);
    }

    @GetMapping("/suggestions/{userId}")
    public List<SkillService.SkillMentorSuggestion> getSuggestedMentorsForUserSkills(@PathVariable Long userId) {
        return skillService.getSuggestedMentorsForUserSkills(userId);
    }

    @PostMapping
    public Skill createSkill(@RequestBody SkillService.CreateSkillRequest request) {
        return skillService.createUserSkill(request);
    }   

    @PutMapping("/{id}")
    public Skill updateSkill(@PathVariable Long id, @RequestBody SkillService.UpdateSkillRequest request) {
        return skillService.updateSkill(id, request);
    }

    @DeleteMapping("/{id}") 
    public void deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
    }
}
