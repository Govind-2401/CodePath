package com.codepath.backend.controller;

import com.codepath.backend.dto.TopicPrerequisiteRequest;
import com.codepath.backend.dto.TopicPrerequisiteResponse;
import com.codepath.backend.service.TopicPrerequisiteService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics/{topicId}/prerequisites")
public class TopicPrerequisiteController {

    private final TopicPrerequisiteService prerequisiteService;

    public TopicPrerequisiteController(
            TopicPrerequisiteService prerequisiteService) {
        this.prerequisiteService = prerequisiteService;
    }

    @PutMapping
    public ResponseEntity<List<TopicPrerequisiteResponse>> setPrerequisites(
            @PathVariable Long topicId,
            @Valid @RequestBody TopicPrerequisiteRequest request) {

        return ResponseEntity.ok(
                prerequisiteService.setPrerequisites(
                        topicId,
                        request.getPrerequisiteTopicIds()
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<TopicPrerequisiteResponse>> getPrerequisites(
            @PathVariable Long topicId) {

        return ResponseEntity.ok(
                prerequisiteService.getPrerequisites(topicId)
        );
    }

    @DeleteMapping("/{prerequisiteId}")
    public ResponseEntity<Void> removePrerequisite(
            @PathVariable Long topicId,
            @PathVariable Long prerequisiteId) {

        prerequisiteService.removePrerequisite(topicId, prerequisiteId);

        return ResponseEntity.noContent().build();
    }
}