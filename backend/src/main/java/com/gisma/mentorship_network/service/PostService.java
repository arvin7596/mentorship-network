package com.gisma.mentorship_network.service;

import org.springframework.stereotype.Service;
import com.gisma.mentorship_network.repository.PostRepository;
import com.gisma.mentorship_network.model.Post;
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
    public Post createPost(CreatePostRequest request) {
        if (!userRepository.existsById(request.author_id())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User with ID " +  request.author_id + " not found.");
        }
        Post newPost = new Post();
        newPost.setTitle(request.title());
        newPost.setDescription(request.description());
        newPost.setAuthor_id(request.author_id());
        return postRepository.save(newPost);
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