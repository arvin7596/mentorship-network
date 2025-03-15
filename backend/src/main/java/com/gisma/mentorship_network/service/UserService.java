package com.gisma.mentorship_network.service;

import com.gisma.mentorship_network.model.User;
import com.gisma.mentorship_network.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getMentors() {
        return userRepository.findMentors();
    }

    public List<User> getMentees() {
        return userRepository.findMentees();
    }
}