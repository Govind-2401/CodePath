package com.codepath.backend.repository;

import com.codepath.backend.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProblemRepository extends JpaRepository<Problem, Long> {

    Optional<Problem> findByProblemNumber(Integer problemNumber);

    List<Problem> findByTitleContainingIgnoreCase(String title);

    List<Problem> findByTitleContainingIgnoreCaseOrProblemNumber(
            String title, Integer problemNumber
    );
}