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
import com.example.blog_app.dto.ApiResponse;
import com.example.blog_app.model.BlogPost;;

@RestController
@RequestMapping("/api/posts")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BlogPost>>> getAllPosts() {
        List<BlogPost> posts = blogService.getAllPosts();
        String message = posts.isEmpty() ? "No blog posts found." : "Blog posts retrieved successfully.";
        return ResponseEntity.ok(new ApiResponse<>(message, posts, 200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BlogPost>> getPostById(@PathVariable String id) {
        BlogPost post = blogService.getPostById(id);
        if (post == null) {
            return ResponseEntity.status(404).body(new ApiResponse<>("Post not found.", null, 404));
        }

        return ResponseEntity.ok(new ApiResponse<>("Post retrieved successfully.", post, 200));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BlogPost>> createPost(@RequestBody BlogPost post) {
        BlogPost createdPost = blogService.createPost(post);
        if(createdPost == null) {
            return ResponseEntity.status(500).body(new ApiResponse<>("Failed to create post.", null, 500));
        }

        return ResponseEntity.status(201).body(new ApiResponse<>("Post created successfully.", createdPost, 201));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BlogPost>> updatePost(@PathVariable String id, @RequestBody BlogPost post) {
        BlogPost updatedPost = blogService.updatePost(id, post);
        if (updatedPost == null) {
            return ResponseEntity.status(404).body(new ApiResponse<>("Post not found. Unable to update.", null, 404))  ;
        }

        return ResponseEntity.ok(new ApiResponse<>("Post updated successfully.", post, 200));
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<BlogPost>> deletePost(@PathVariable String id) {
        BlogPost deletedPost = blogService.deletePost(id);
        if (deletedPost ==  null) {
            return ResponseEntity.status(404).body(new ApiResponse<>("Post not found. Unable to delete.", null, 404));
        }
        return ResponseEntity.ok(new ApiResponse<>("Post deleted successfully.", deletedPost, 200));
    }
}
