package com.codepath.backend.service.impl;

import com.codepath.backend.dto.ProblemTopicResponse;
import com.codepath.backend.entity.Problem;
import com.codepath.backend.entity.Topic;
import com.codepath.backend.exception.ResourceNotFoundException;
import com.codepath.backend.repository.ProblemRepository;
import com.codepath.backend.repository.TopicRepository;
import com.codepath.backend.service.ProblemTopicService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ProblemTopicServiceImpl implements ProblemTopicService {

    private final ProblemRepository problemRepository;
    private final TopicRepository topicRepository;

    public ProblemTopicServiceImpl(
            ProblemRepository problemRepository,
            TopicRepository topicRepository) {
        this.problemRepository = problemRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public List<ProblemTopicResponse> setTopics(Long problemId, List<Long> topicIds) {

        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Problem not found with ID: " + problemId));

        Set<Topic> topics = new HashSet<>();

        for (Long topicId : new HashSet<>(topicIds)) {

            Topic topic = topicRepository.findById(topicId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Topic not found with ID: " + topicId));

            topics.add(topic);
        }

        problem.setTopics(topics);

        problemRepository.save(problem);

        return topics.stream()
                .map(topic -> new ProblemTopicResponse(
                        topic.getId(),
                        topic.getName()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProblemTopicResponse> getTopics(Long problemId) {

        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Problem not found with ID: " + problemId));

        return problem.getTopics().stream()
                .map(topic -> new ProblemTopicResponse(
                        topic.getId(),
                        topic.getName()))
                .toList();
    }

    @Override
    public void removeTopic(Long problemId, Long topicId) {

        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Problem not found with ID: " + problemId));

        boolean removed = problem.getTopics()
                .removeIf(topic -> topic.getId().equals(topicId));

        if (!removed) {
            throw new ResourceNotFoundException(
                    "Topic mapping not found for problem ID: " + problemId);
        }

        problemRepository.save(problem);
    }
}