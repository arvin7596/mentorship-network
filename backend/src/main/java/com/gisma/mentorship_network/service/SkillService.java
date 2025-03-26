package com.gisma.mentorship_network.service;

import com.gisma.mentorship_network.model.Skill;
import com.gisma.mentorship_network.model.SkillLevel;
import com.gisma.mentorship_network.repository.SkillRepository;
import com.gisma.mentorship_network.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SkillService {
    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public SkillService(SkillRepository skillRepository, UserRepository userRepository) {
        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
    }

    public List<Skill> getAllUserSkills(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + userId + " not found");
        }
        return skillRepository.getUserSkills(userId);
    }

    public record CreateSkillRequest(String name, Long user_id, SkillLevel level) {}
    public Skill createUserSkill(CreateSkillRequest request) {
        if (skillRepository.existsByUserIdAndName(request.user_id,request.name)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Skill already existed");
        }

        Skill skill = new Skill();
        skill.setName(request.name);    
        skill.setLevel(request.level);
        skill.setUserId(request.user_id);
        return skillRepository.save(skill);
    }


    public record UpdateSkillRequest(String name, SkillLevel level) {}
    public Skill updateSkill(Long id, UpdateSkillRequest request) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found"));
        skill.setName(request.name);
        skill.setLevel(request.level);  
        return skillRepository.save(skill);
    }

    public void deleteSkill(Long id) {
        if(!skillRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Skill with ID " + id + " not found.");
        }
        skillRepository.deleteById(id);
    }
    

    public record MentorDetail(Long id, String first_name, String last_name, String email, String country, String city) {}
    public record SkillMentorSuggestion(String skillName, List<MentorDetail> mentors) {}

    public List<SkillMentorSuggestion> getSuggestedMentorsForUserSkills(Long userId) {
        // Get all users skills
        List<Skill> userSkills = getAllUserSkills(userId);
        
        // For each skill, find mentors
        return userSkills.stream().map(skill -> {
            List<MentorDetail> mentors = skillRepository.findMentorsWithSkill(skill.getName());
            return new SkillMentorSuggestion(skill.getName(), mentors);
        }).toList();
    }
    
}
