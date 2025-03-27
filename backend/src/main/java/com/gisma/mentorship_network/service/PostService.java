package com.gisma.mentorship_network.service;

import org.springframework.stereotype.Service;
import com.gisma.mentorship_network.repository.PostRepository;
import com.gisma.mentorship_network.model.Post;
import com.gisma.mentorship_network.model.PostDTO;
import com.gisma.mentorship_network.model.User;
import com.gisma.mentorship_network.model.UserDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.gisma.mentorship_network.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        if (!postRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Post with ID " + id + " not found.");
        }
        return postRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Post with ID " + id + " not found."));
    }

    public record CreatePostRequest(
        @NotBlank(message = "Title is required")
        @Size(min = 2, max = 50, message = "Title must be between 2 and 50 characters")
        String title,
        String description,
        @NotNull(message = "User ID is required")
        Long author_id) {}
    public PostDTO createPost(CreatePostRequest request) {
        Post newPost = new Post();
        // if(request.title() == null || request.title().isEmpty() || request.title().length() < 2 || request.title().length() > 50) {
        //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title must be between 2 and 50 characters");
        // }
        newPost.setTitle(request.title);
        Optional.ofNullable(request.description).ifPresent(newPost::setDescription);

        User author = userRepository.findById(request.author_id).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "User with ID " +  request.author_id + " not found."));
        newPost.setAuthor(author);
        Post savedPost = postRepository.save(newPost);
        UserDTO authorDTO = new UserDTO(savedPost.getAuthor().getId(), savedPost.getAuthor().getFirst_name(), savedPost.getAuthor().getLast_name(), savedPost.getAuthor().getEmail());
        return new PostDTO(savedPost.getId(), savedPost.getTitle(), savedPost.getDescription(), authorDTO);
    }

    public record UpdatePostRequest(
        @NotBlank(message = "Title is required")
        @Size(min = 2, max = 50, message = "Title must be between 2 and 50 characters")
        String title,
        
        String description) {}
    public Post updatePost(Long id, UpdatePostRequest request) {
        if (!postRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Post with ID " + id + " not found.");
        }
        Post existingPost = getPostById(id);
        Optional.ofNullable(request.title).ifPresent(existingPost::setTitle);
        Optional.ofNullable(request.description).ifPresent(existingPost::setDescription);
        return postRepository.save(existingPost);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}