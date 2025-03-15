package com.gisma.mentorship_network.controller;

import com.gisma.mentorship_network.model.User;
import com.gisma.mentorship_network.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
}