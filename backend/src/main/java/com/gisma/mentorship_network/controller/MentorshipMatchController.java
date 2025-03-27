package com.gisma.mentorship_network.controller;

import com.gisma.mentorship_network.model.MentorshipMatch;
import com.gisma.mentorship_network.service.MentorshipMatchService;
import com.gisma.mentorship_network.service.MentorshipMatchService.MentorshipMatchDTO;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
@RestController
@RequestMapping("/api/mentorship-matches")
public class MentorshipMatchController {
    private final MentorshipMatchService mentorshipMatchService;

    public MentorshipMatchController(MentorshipMatchService mentorshipMatchService) {
        this.mentorshipMatchService = mentorshipMatchService;
    }

     @GetMapping
     public List<MentorshipMatch> getAllMentorshipMatches() {
         return mentorshipMatchService.getAllMentorshipMatches();
     }

     @GetMapping("/{id}")
     public MentorshipMatch getMentorshipMatchById(@PathVariable Long id) {
         return mentorshipMatchService.getMentorshipMatchById(id);
     }

     @PostMapping
     public ResponseEntity<MentorshipMatchDTO> createMentorshipMatch(@RequestBody MentorshipMatchService.CreateMentorshipMatchRequest mentorshipMatch) {
        MentorshipMatchDTO createdMentorshipMatch = mentorshipMatchService.createMentorshipMatch(mentorshipMatch);
         return ResponseEntity.status(HttpStatus.CREATED).body(createdMentorshipMatch);
     }

     @PutMapping("/{id}")
     public ResponseEntity<MentorshipMatchDTO> updateMentorshipMatch(@PathVariable Long id, @RequestBody MentorshipMatch mentorshipMatch) {
        MentorshipMatchDTO updatedMentorshipMatch = mentorshipMatchService.updateMentorshipMatch(id, mentorshipMatch);
         return ResponseEntity.ok(updatedMentorshipMatch);
     }
    
     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteMentorshipMatch(@PathVariable Long id) {
         mentorshipMatchService.deleteMentorshipMatch(id);
         return ResponseEntity.noContent().build();
     }
            
            
}
