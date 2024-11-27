package com.example.blog_app.repository;

import com.example.blog_app.model.BlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for BlogPost documents.
 * Extends MongoRepository to provide CRUD operations and custom query support.
 */
public interface BlogRepository extends MongoRepository<BlogPost, String> {

}
