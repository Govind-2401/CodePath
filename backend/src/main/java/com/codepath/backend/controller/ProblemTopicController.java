package com.codepath.backend.controller;

import com.codepath.backend.dto.ProblemTopicRequest;
import com.codepath.backend.dto.ProblemTopicResponse;
import com.codepath.backend.service.ProblemTopicService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problems/{problemId}/topics")
public class ProblemTopicController {

    private final ProblemTopicService problemTopicService;

    public ProblemTopicController(ProblemTopicService problemTopicService) {
        this.problemTopicService = problemTopicService;
    }

    @PutMapping
    public ResponseEntity<List<ProblemTopicResponse>> setTopics(
            @PathVariable Long problemId,
            @Valid @RequestBody ProblemTopicRequest request) {

        return ResponseEntity.ok(
                problemTopicService.setTopics(problemId, request.getTopicIds()));
    }

    @GetMapping
    public ResponseEntity<List<ProblemTopicResponse>> getTopics(
            @PathVariable Long problemId) {

        return ResponseEntity.ok(
                problemTopicService.getTopics(problemId));
    }

    @DeleteMapping("/{topicId}")
    public ResponseEntity<Void> removeTopic(
            @PathVariable Long problemId,
            @PathVariable Long topicId) {

        problemTopicService.removeTopic(problemId, topicId);

        return ResponseEntity.noContent().build();
    }
}