package com.gisma.mentorship_network;

import com.gisma.mentorship_network.user.User;
import com.gisma.mentorship_network.user.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/")
public class MentorshipNetworkApplication {

	private final UserRepository userRepository;

	public MentorshipNetworkApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(MentorshipNetworkApplication.class, args);
	}

	@GetMapping("users")
	public List<User> getUsers() {
		return userRepository
				.findAll();
	}
}
