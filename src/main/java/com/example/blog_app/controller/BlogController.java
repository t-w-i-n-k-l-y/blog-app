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
import com.example.blog_app.model.BlogPost;;

@RestController
@RequestMapping("/api/posts")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public List<BlogPost> getAllPosts() {
        return blogService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getPostById(@PathVariable String id) {
        BlogPost post = blogService.getPostById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(post);
    }

    @PostMapping
    public BlogPost createPost(@RequestBody BlogPost post) {
        return blogService.createPost(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable String id, @RequestBody BlogPost post) {
        BlogPost updatedPost = blogService.updatePost(id, post);
        if (updatedPost == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedPost);
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<BlogPost> deletePost(@PathVariable String id) {
        BlogPost deletedPost = blogService.deletePost(id);
        if (deletedPost ==  null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedPost);
    }
}
