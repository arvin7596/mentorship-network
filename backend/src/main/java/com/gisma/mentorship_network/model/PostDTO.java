package com.gisma.mentorship_network.model;

import java.util.Objects;

public class PostDTO {
    private Long id;
    private String title;
    private String description;
    private UserDTO author;

    public PostDTO() {}

    public PostDTO(Long id, String title, String description, UserDTO author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PostDTO postDTO = (PostDTO) o;
        return Objects.equals(id, postDTO.id) && Objects.equals(title, postDTO.title) && Objects.equals(description, postDTO.description) && Objects.equals(author, postDTO.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, author);
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                '}';
    }
}