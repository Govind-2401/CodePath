package com.codepath.backend.dto;

import com.codepath.backend.entity.Difficulty;
import java.time.LocalDateTime;

public class ProblemResponse {

    private Long id;
    private Integer problemNumber;
    private String title;
    private Difficulty difficulty;
    private String description;
    private String leetcodeUrl;
    private LocalDateTime createdAt;

    public ProblemResponse(Long id, Integer problemNumber, String title,
                           Difficulty difficulty, String description,
                           String leetcodeUrl, LocalDateTime createdAt) {
        this.id = id;
        this.problemNumber = problemNumber;
        this.title = title;
        this.difficulty = difficulty;
        this.description = description;
        this.leetcodeUrl = leetcodeUrl;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public Integer getProblemNumber() { return problemNumber; }
    public String getTitle() { return title; }
    public Difficulty getDifficulty() { return difficulty; }
    public String getDescription() { return description; }
    public String getLeetcodeUrl() { return leetcodeUrl; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}