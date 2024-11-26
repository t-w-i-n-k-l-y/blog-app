package com.example.blog_app.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Document(collection = "blogPost") // mongoDB collection
@Data
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
    private String createdAt;

    @LastModifiedDate
    private String lastUpdate;
}
