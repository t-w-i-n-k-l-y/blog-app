package com.example.blog_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.blog_app.model.BlogPost;
import com.example.blog_app.repository.BlogRepository;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<BlogPost> getAllPosts() {
        return blogRepository.findAll();
    }

    public BlogPost getPostById(String id) {
        return blogRepository.findById(id).orElse(null);
    }

    public BlogPost createPost(BlogPost post) {
        post.setCreatedAt(LocalDateTime.now().toString());
        return blogRepository.save(post);
    }

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

    public BlogPost deletePost(String id) {
        Optional<BlogPost> existingPost = blogRepository.findById(id);
        if(existingPost.isPresent()) {
            blogRepository.deleteById(id); 
            return existingPost.get(); 
        }
        return null;
    }

}
