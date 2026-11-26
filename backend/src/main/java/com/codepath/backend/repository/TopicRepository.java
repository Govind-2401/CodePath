package com.codepath.backend.repository;

import com.codepath.backend.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Optional<Topic> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);

    List<Topic> findByNameContainingIgnoreCase(String name);
}