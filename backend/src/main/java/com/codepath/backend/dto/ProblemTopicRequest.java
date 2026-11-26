package com.codepath.backend.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class ProblemTopicRequest {

    @NotNull
    private List<@NotNull Long> topicIds;

    public List<Long> getTopicIds() {
        return topicIds;
    }

    public void setTopicIds(List<Long> topicIds) {
        this.topicIds = topicIds;
    }
}