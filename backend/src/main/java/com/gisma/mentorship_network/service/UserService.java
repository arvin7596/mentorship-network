package com.gisma.mentorship_network.service;

import com.gisma.mentorship_network.model.User;
import com.gisma.mentorship_network.repository.UserRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }


    public record CreateUserRequest(
            @NotBlank(message = "First name is required")
            @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
            String first_name,

            @NotBlank(message = "Last name is required")
            @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
            String last_name,

            @NotBlank(message = "Email is required")
            @Email(message = "Invalid email format")
            String email,

            @NotBlank(message = "Password is required")
            @Size(min = 4, max = 12, message = "Password must be between 4 and 12 characters")
            String password,

            Boolean is_mentor,
            Boolean is_mentee
    ) {
    }

    public User createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.email)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Email already registered");
        }

        User user = new User();
        user.setFirst_name(request.first_name);
        user.setLast_name(request.last_name);
        user.setEmail(request.email);
        user.setPassword(request.password);
        user.setIs_mentor(Optional.ofNullable(request.is_mentor).orElse(false));
        user.setIs_mentee(Optional.ofNullable(request.is_mentee).orElse(true));
        user.setCreated_at(LocalDateTime.now());
        user.setIs_active(true);

        return userRepository.save(user);
    }


    public record UpdateUserRequest(
            @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
            String first_name,

            @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
            String last_name,

            @Email(message = "Invalid email format")
            String email,

            Boolean is_mentor,
            Boolean is_mentee,
            String country,
            String city,
            String phone,
            Date birth_date
    ) {
    }

    public User updateUser(Integer id, UpdateUserRequest user) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User with ID " + id + " not found.");
        }

        User userToUpdate = userRepository.findById(id).orElse(null);

        Optional.ofNullable(user.first_name).ifPresent(userToUpdate::setFirst_name);
        Optional.ofNullable(user.last_name).ifPresent(userToUpdate::setLast_name);
        Optional.ofNullable(user.email).ifPresent(email -> {
            if (userRepository.existsByEmail(user.email)) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Email is already in use");
            }
            userToUpdate.setEmail(email);
        });
        Optional.ofNullable(user.is_mentor).ifPresent(userToUpdate::setIs_mentor);
        Optional.ofNullable(user.is_mentee).ifPresent(userToUpdate::setIs_mentee);
        Optional.ofNullable(user.country).ifPresent(userToUpdate::setCountry);
        Optional.ofNullable(user.city).ifPresent(userToUpdate::setCity);
        Optional.ofNullable(user.phone).ifPresent(userToUpdate::setPhone);
        Optional.ofNullable(user.birth_date).ifPresent(userToUpdate::setBirth_date);

        return userRepository.save(userToUpdate);

    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User with ID " + id + " not found.");
        }
        userRepository.deleteById(id);
    }

}