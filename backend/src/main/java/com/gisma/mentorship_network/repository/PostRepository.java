package com.gisma.mentorship_network.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gisma.mentorship_network.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    
}
