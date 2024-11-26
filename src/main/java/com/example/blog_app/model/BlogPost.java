package com.example.blog_app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "blogPost") // mongoDB collection
@Data
public class BlogPost {
    @Id
    private String id;
    private String title;
    private String content;
    private String author;
    private String createdAt;
}
