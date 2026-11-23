package com.codepath.backend.dto;

import com.codepath.backend.entity.Difficulty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProblemRequest {

    @NotNull
    @Positive
    private Integer problemNumber;

    @NotBlank
    private String title;

    @NotNull
    private Difficulty difficulty;

    private String description;

    private String leetcodeUrl;

    public Integer getProblemNumber() {
        return problemNumber;
    }

    public void setProblemNumber(Integer problemNumber) {
        this.problemNumber = problemNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLeetcodeUrl() {
        return leetcodeUrl;
    }

    public void setLeetcodeUrl(String leetcodeUrl) {
        this.leetcodeUrl = leetcodeUrl;
    }
}