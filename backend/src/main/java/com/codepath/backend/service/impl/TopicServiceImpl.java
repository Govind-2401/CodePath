package com.codepath.backend.service.impl;

import com.codepath.backend.dto.TopicRequest;
import com.codepath.backend.dto.TopicResponse;
import com.codepath.backend.entity.Topic;
import com.codepath.backend.exception.DuplicateResourceException;
import com.codepath.backend.exception.ResourceNotFoundException;
import com.codepath.backend.repository.TopicRepository;
import com.codepath.backend.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public TopicResponse createTopic(TopicRequest request) {

        if (topicRepository.existsByNameIgnoreCase(request.getName())) {
            throw new DuplicateResourceException("Topic already exists");
        }

        Topic topic = new Topic();
        topic.setName(request.getName());
        topic.setDescription(request.getDescription());

        Topic savedTopic = topicRepository.save(topic);

        return mapToResponse(savedTopic);
    }

    @Override
    public List<TopicResponse> getAllTopics() {
        return topicRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public TopicResponse getTopicById(Long id) {

        Topic topic = topicRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Topic not found with ID: " + id));

        return mapToResponse(topic);
    }

    @Override
    public List<TopicResponse> searchTopics(String keyword) {
        return topicRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public TopicResponse updateTopic(Long id, TopicRequest request) {

        Topic topic = topicRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Topic not found with ID: " + id));

        boolean nameChanged = !topic.getName().equalsIgnoreCase(request.getName());

        if (nameChanged && topicRepository.existsByNameIgnoreCase(request.getName())) {
            throw new DuplicateResourceException("Topic already exists");
        }

        topic.setName(request.getName());
        topic.setDescription(request.getDescription());

        Topic updatedTopic = topicRepository.save(topic);

        return mapToResponse(updatedTopic);
    }

    @Override
    public void deleteTopic(Long id) {

        Topic topic = topicRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Topic not found with ID: " + id));

        topicRepository.delete(topic);
    }

    private TopicResponse mapToResponse(Topic topic) {
        return new TopicResponse(
                topic.getId(),
                topic.getName(),
                topic.getDescription(),
                topic.getCreatedAt()
        );
    }
}