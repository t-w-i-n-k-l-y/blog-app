package com.example.blog_app.dto;

/**
 * Generic class to represent a standard API response structure.
 * 
 * @param <T> the type of data being returned in the response
 */
public class ApiResponse<T> {

    // Message describing the outcome of the API operation
    private String message;

    // Generic data payload for the response
    private T data;

    // HTTP status code associated with the response
    private int statusCode;

    /**
     * Constructor to initialize all fields of the API response.
     *
     * @param message    a descriptive message about the API response
     * @param data       the data being returned (generic type)
     * @param statusCode the HTTP status code
     */
    public ApiResponse(String message, T data, int statusCode) {
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
    }

    // Getters and setters for the fields
    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
