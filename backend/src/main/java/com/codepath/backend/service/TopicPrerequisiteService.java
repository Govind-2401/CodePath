package com.codepath.backend.service;

import com.codepath.backend.dto.TopicPrerequisiteResponse;
import java.util.List;

public interface TopicPrerequisiteService {

    List<TopicPrerequisiteResponse> setPrerequisites(
            Long topicId,
            List<Long> prerequisiteTopicIds
    );

    List<TopicPrerequisiteResponse> getPrerequisites(Long topicId);

    void removePrerequisite(Long topicId, Long prerequisiteId);
}