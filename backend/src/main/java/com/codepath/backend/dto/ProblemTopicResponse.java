package com.codepath.backend.dto;

public class ProblemTopicResponse {

    private Long id;
    private String name;

    public ProblemTopicResponse(Long id, String name) {
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