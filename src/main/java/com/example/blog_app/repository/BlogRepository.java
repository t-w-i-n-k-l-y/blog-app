package com.example.blog_app.repository;

import com.example.blog_app.model.BlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogRepository extends MongoRepository<BlogPost, String> {

}
