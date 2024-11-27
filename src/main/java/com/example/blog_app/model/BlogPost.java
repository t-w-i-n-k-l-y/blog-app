package com.example.blog_app.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Represents a blog post in the application.
 * This class is mapped to a MongoDB collection and includes validation rules
 * for the fields using Jakarta Bean Validation annotations.
 */
@Document(collection = "blogPost")  // mongoDB collection name
@Data   // Generates getters, setters, toString, equals, and hashCode methods using Lombok
public class BlogPost {
    @Id
    private String id;

    @NotBlank(message = "{blogpost.title.not_blank}")
    @Size(min = 5, max = 100, message = "{blogpost.title.size}")
    private String title;
    
    @NotBlank(message = "{blogpost.content.not_blank}")
    @Size(min = 10, message = "{blogpost.content.size}")
    private String content;
   
   @NotBlank(message = "{blogpost.author.not_blank}")
    private String author;

    @CreatedDate
    private String createdAt;    // Automatically set to the creation timestamp

    @LastModifiedDate
    private String lastUpdate;  // Automatically updated to the last modification timestamp
}
