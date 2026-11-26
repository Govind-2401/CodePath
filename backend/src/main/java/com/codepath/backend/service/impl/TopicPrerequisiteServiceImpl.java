package com.codepath.backend.service.impl;

import com.codepath.backend.dto.TopicPrerequisiteResponse;
import com.codepath.backend.entity.Topic;
import com.codepath.backend.exception.ResourceNotFoundException;
import com.codepath.backend.repository.TopicRepository;
import com.codepath.backend.service.TopicPrerequisiteService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TopicPrerequisiteServiceImpl implements TopicPrerequisiteService {

    private final TopicRepository topicRepository;

    public TopicPrerequisiteServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    @Transactional
    public List<TopicPrerequisiteResponse> setPrerequisites(
            Long topicId,
            List<Long> prerequisiteTopicIds) {

        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Topic not found with ID: " + topicId
                ));

        Set<Topic> prerequisites = new HashSet<>();

        for (Long prerequisiteId : prerequisiteTopicIds) {

            if (topicId.equals(prerequisiteId)) {
                throw new IllegalArgumentException(
                        "A topic cannot be its own prerequisite"
                );
            }

            Topic prerequisite = topicRepository.findById(prerequisiteId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Prerequisite topic not found with ID: " + prerequisiteId
                    ));

            if (hasPrerequisitePath(prerequisite, topicId, new HashSet<>())) {
                throw new IllegalArgumentException(
                        "Circular prerequisite relationship is not allowed"
                );
            }

            prerequisites.add(prerequisite);
        }

        topic.setPrerequisites(prerequisites);

        Topic savedTopic = topicRepository.save(topic);

        return mapToResponse(savedTopic.getPrerequisites());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TopicPrerequisiteResponse> getPrerequisites(Long topicId) {

        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Topic not found with ID: " + topicId
                ));

        return mapToResponse(topic.getPrerequisites());
    }

    @Override
    @Transactional
    public void removePrerequisite(Long topicId, Long prerequisiteId) {

        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Topic not found with ID: " + topicId
                ));

        Topic prerequisite = topicRepository.findById(prerequisiteId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Prerequisite topic not found with ID: " + prerequisiteId
                ));

        if (!topic.getPrerequisites().remove(prerequisite)) {
            throw new ResourceNotFoundException(
                    "Prerequisite relationship not found"
            );
        }

        topicRepository.save(topic);
    }

    private boolean hasPrerequisitePath(
            Topic current,
            Long targetId,
            Set<Long> visited) {

        if (current.getId().equals(targetId)) {
            return true;
        }

        if (!visited.add(current.getId())) {
            return false;
        }

        for (Topic prerequisite : current.getPrerequisites()) {
            if (hasPrerequisitePath(prerequisite, targetId, visited)) {
                return true;
            }
        }

        return false;
    }

    private List<TopicPrerequisiteResponse> mapToResponse(Set<Topic> topics) {

        return topics.stream()
                .map(topic -> new TopicPrerequisiteResponse(
                        topic.getId(),
                        topic.getName()
                ))
                .collect(Collectors.toList());
    }
}