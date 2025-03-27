package com.gisma.mentorship_network.service;

import org.springframework.stereotype.Service;
import com.gisma.mentorship_network.repository.PostRepository;
import com.gisma.mentorship_network.model.Post;
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

import static com.gisma.mentorship_network.service.UserService.getUserDTO;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public record PostDTO(Long id, String title, String description, UserDTO author) {}

    PostDTO getPostDTO(Post post) {
        return new PostDTO(post.getId(), post.getTitle(), post.getDescription(), getUserDTO(post.getAuthor()));
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream().map(this::getPostDTO).toList();
    }

    public PostDTO getPostById(Long id) {
        if (!postRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Post with ID " + id + " not found.");
        }
        return getPostDTO(postRepository.findById(id).orElseThrow());
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
        return getPostDTO(savedPost);
    }

    public record UpdatePostRequest(
        @NotBlank(message = "Title is required")
        @Size(min = 2, max = 50, message = "Title must be between 2 and 50 characters")
        String title,
        
        String description) {}
    public PostDTO updatePost(Long id, UpdatePostRequest request) {
        if (!postRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Post with ID " + id + " not found.");
        }
        Post existingPost = postRepository.findById(id).orElseThrow();
        Optional.ofNullable(request.title).ifPresent(existingPost::setTitle);
        Optional.ofNullable(request.description).ifPresent(existingPost::setDescription);
        Post updatedPost = postRepository.save(existingPost);
        return getPostDTO(updatedPost);
    }

    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Post with ID " + id + " not found.");
        }
        postRepository.deleteById(id);
    }
}