package com.gisma.mentorship_network.repository;

import com.gisma.mentorship_network.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.is_mentor = true")
    List<User> findMentors();

    @Query("SELECT u FROM User u WHERE u.is_mentee = true")
    List<User> findMentees();

    boolean existsByEmail(@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email);

    boolean existsById(Long id);

    Optional<Object> findByEmail(String email);
}

