package com.codepath.backend.service;

import com.codepath.backend.dto.ProblemTopicResponse;

import java.util.List;

public interface ProblemTopicService {

    List<ProblemTopicResponse> setTopics(Long problemId, List<Long> topicIds);

    List<ProblemTopicResponse> getTopics(Long problemId);

    void removeTopic(Long problemId, Long topicId);
}