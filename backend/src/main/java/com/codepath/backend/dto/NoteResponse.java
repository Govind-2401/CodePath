package com.codepath.backend.dto;

import java.time.LocalDateTime;

public class NoteResponse {

    private Long id;
    private String title;
    private String content;
    private Long topicId;
    private String topicName;
    private LocalDateTime createdAt;

    public NoteResponse(
            Long id,
            String title,
            String content,
            Long topicId,
            String topicName,
            LocalDateTime createdAt) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.topicId = topicId;
        this.topicName = topicName;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}