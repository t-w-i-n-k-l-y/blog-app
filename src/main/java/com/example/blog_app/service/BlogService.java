package com.example.blog_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.blog_app.model.BlogPost;
import com.example.blog_app.repository.BlogRepository;

/**
 * Service class for managing blog posts.
 * Handles business logic and interacts with the BlogRepository.
 */@Service
public class BlogService {
    private final BlogRepository blogRepository;

    /**
     * Constructor to inject the BlogRepository dependency.
     *
     * @param blogRepository the repository for BlogPost entities
     */
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    /**
     * Retrieves all blog posts.
     *
     * @return a list of all BlogPost entities
     */
    public List<BlogPost> getAllPosts() {
        return blogRepository.findAll();
    }

    /**
     * Retrieves a blog post by its ID.
     *
     * @param id the ID of the blog post
     * @return the BlogPost entity, or null if not found
     */
    public BlogPost getPostById(String id) {
        return blogRepository.findById(id).orElse(null);
    }

    /**
     * Creates a new blog post.
     *
     * @param post the BlogPost entity to create
     * @return the saved BlogPost entity
     */
    public BlogPost createPost(BlogPost post) {
        post.setCreatedAt(LocalDateTime.now().toString());
        return blogRepository.save(post);
    }

    /**
     * Updates an existing blog post.
     *
     * @param id the ID of the blog post to update
     * @param updatedPost the BlogPost entity with updated information
     * @return the updated BlogPost entity, or null if the post does not exist
     */
    public BlogPost updatePost(String id, BlogPost updatedPost) {
        Optional<BlogPost> existingPost = blogRepository.findById(id);
        if(existingPost.isPresent()) {
            BlogPost post = existingPost.get();
            post.setTitle(updatedPost.getTitle());
            post.setContent(updatedPost.getContent());
            post.setAuthor(updatedPost.getAuthor());
            return blogRepository.save(post);
        }

        return null;
    }

    /**
     * Deletes a blog post by its ID.
     *
     * @param id the ID of the blog post to delete
     * @return the deleted BlogPost entity, or null if the post does not exist
     */
    public BlogPost deletePost(String id) {
        Optional<BlogPost> existingPost = blogRepository.findById(id);
        if(existingPost.isPresent()) {
            blogRepository.deleteById(id); 
            return existingPost.get(); 
        }
        return null;
    }

}
