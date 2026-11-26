package com.codepath.backend.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class TopicPrerequisiteRequest {

    @NotNull(message = "Prerequisite topic IDs are required")
    private List<@NotNull Long> prerequisiteTopicIds;

    public List<Long> getPrerequisiteTopicIds() {
        return prerequisiteTopicIds;
    }

    public void setPrerequisiteTopicIds(List<Long> prerequisiteTopicIds) {
        this.prerequisiteTopicIds = prerequisiteTopicIds;
    }
}