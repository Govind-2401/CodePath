package com.codepath.backend.dto;

public class TopicPrerequisiteResponse {

    private Long id;
    private String name;

    public TopicPrerequisiteResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}