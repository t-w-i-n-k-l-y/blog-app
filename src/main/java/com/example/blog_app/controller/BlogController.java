package com.example.blog_app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog_app.service.BlogService;

import jakarta.validation.Valid;

import com.example.blog_app.dto.ApiResponse;
import com.example.blog_app.model.BlogPost;;

/**
 * Controller for handling blog post-related HTTP requests.
 * Provides endpoints for CRUD operations on blog posts.
 */
@RestController
@RequestMapping("/api/posts")
public class BlogController {
    private final BlogService blogService;

    /**
     * Constructor to inject the BlogService dependency.
     *
     * @param blogService the service layer for BlogPost operations
     */
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * Retrieves all blog posts.
     *
     * @return a ResponseEntity containing an ApiResponse with a list of BlogPost objects
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<BlogPost>>> getAllPosts() {
        List<BlogPost> posts = blogService.getAllPosts();
        String message = posts.isEmpty() ? "No blog posts found." : "Blog posts retrieved successfully.";
        return ResponseEntity.ok(new ApiResponse<>(message, posts, 200));
    }

    /**
     * Retrieves a specific blog post by ID.
     *
     * @param id the ID of the blog post
     * @return a ResponseEntity containing an ApiResponse with the BlogPost object or a 404 status if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BlogPost>> getPostById(@PathVariable String id) {
        BlogPost post = blogService.getPostById(id);
        if (post == null) {
            return ResponseEntity.status(404).body(new ApiResponse<>("Post not found.", null, 404));
        }

        return ResponseEntity.ok(new ApiResponse<>("Post retrieved successfully.", post, 200));
    }

    /**
     * Creates a new blog post.
     *
     * @param post the BlogPost object to create, validated by @Valid
     * @return a ResponseEntity containing an ApiResponse with the created BlogPost object or a 500 status if creation fails
     */
    @PostMapping
    public ResponseEntity<ApiResponse<BlogPost>> createPost(@Valid @RequestBody BlogPost post) {
        BlogPost createdPost = blogService.createPost(post);
        if(createdPost == null) {
            return ResponseEntity.status(500).body(new ApiResponse<>("Failed to create post.", null, 500));
        }

        return ResponseEntity.status(201).body(new ApiResponse<>("Post created successfully.", createdPost, 201));
    }

    /**
     * Updates an existing blog post.
     *
     * @param id   the ID of the blog post to update
     * @param post the updated BlogPost object, validated by @Valid
     * @return a ResponseEntity containing an ApiResponse with the updated BlogPost object or a 404 status if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BlogPost>> updatePost(@PathVariable String id, @Valid @RequestBody BlogPost post) {
        BlogPost updatedPost = blogService.updatePost(id, post);
        if (updatedPost == null) {
            return ResponseEntity.status(404).body(new ApiResponse<>("Post not found. Unable to update.", null, 404))  ;
        }

        return ResponseEntity.ok(new ApiResponse<>("Post updated successfully.", updatedPost, 200));
    }   

    /**
     * Deletes a blog post by ID.
     *
     * @param id the ID of the blog post to delete
     * @return a ResponseEntity containing an ApiResponse with the deleted BlogPost object or a 404 status if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<BlogPost>> deletePost(@PathVariable String id) {
        BlogPost deletedPost = blogService.deletePost(id);
        if (deletedPost ==  null) {
            return ResponseEntity.status(404).body(new ApiResponse<>("Post not found. Unable to delete.", null, 404));
        }
        return ResponseEntity.ok(new ApiResponse<>("Post deleted successfully.", deletedPost, 200));
    }
}
