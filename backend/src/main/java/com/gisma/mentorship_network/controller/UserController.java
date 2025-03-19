package com.gisma.mentorship_network.controller;

import com.gisma.mentorship_network.model.Skill;
import com.gisma.mentorship_network.model.User;
import com.gisma.mentorship_network.service.SkillService;
import com.gisma.mentorship_network.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final SkillService skillService;

    public UserController(UserService userService, SkillService skillService) {
        this.userService = userService;
        this.skillService = skillService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/mentors")
    public List<User> getMentors() {
        return userService.getMentors();
    }

    @GetMapping("/mentees")
    public List<User> getMentees() {
        return userService.getMentees();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User with ID " + id + " not found."));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserService.CreateUserRequest request) {
        User createdUser = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody UserService.UpdateUserRequest request) {
        User updatedUser = userService.updateUser(id, request);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/skills")
    public List<Skill> getUserSkills(@PathVariable Long userId) {
        return skillService.getAllUserSkills(userId);
    }
}