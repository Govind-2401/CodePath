package com.codepath.backend.controller;

import com.codepath.backend.dto.TopicRequest;
import com.codepath.backend.dto.TopicResponse;
import com.codepath.backend.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    // Create Topic
    @PostMapping
    public ResponseEntity<TopicResponse> createTopic(
            @Valid @RequestBody TopicRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(topicService.createTopic(request));
    }

    // Get All Topics
    @GetMapping
    public ResponseEntity<List<TopicResponse>> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }

    // Get Topic By ID
    @GetMapping("/{id}")
    public ResponseEntity<TopicResponse> getTopicById(
            @PathVariable Long id) {

        return ResponseEntity.ok(topicService.getTopicById(id));
    }

    // Search Topics
    @GetMapping("/search")
    public ResponseEntity<List<TopicResponse>> searchTopics(
            @RequestParam String keyword) {

        return ResponseEntity.ok(topicService.searchTopics(keyword));
    }

    // Update Topic
    @PutMapping("/{id}")
    public ResponseEntity<TopicResponse> updateTopic(
            @PathVariable Long id,
            @Valid @RequestBody TopicRequest request) {

        return ResponseEntity.ok(topicService.updateTopic(id, request));
    }

    // Delete Topic
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {

        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}