package com.codepath.backend.dto;

import java.time.LocalDateTime;

public class TopicResponse {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;

    public TopicResponse(Long id, String name, String description, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}