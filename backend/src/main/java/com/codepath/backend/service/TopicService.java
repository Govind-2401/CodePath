package com.codepath.backend.service;

import com.codepath.backend.dto.TopicRequest;
import com.codepath.backend.dto.TopicResponse;

import java.util.List;

public interface TopicService {

    TopicResponse createTopic(TopicRequest request);

    List<TopicResponse> getAllTopics();

    TopicResponse getTopicById(Long id);

    List<TopicResponse> searchTopics(String keyword);

    TopicResponse updateTopic(Long id, TopicRequest request);

    void deleteTopic(Long id);
}