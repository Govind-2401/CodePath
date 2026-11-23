package com.codepath.backend.service;

import com.codepath.backend.dto.ProblemRequest;
import com.codepath.backend.dto.ProblemResponse;

import java.util.List;

public interface ProblemService {

    ProblemResponse createProblem(ProblemRequest request);

    List<ProblemResponse> getAllProblems();

    ProblemResponse getProblemById(Long id);

    List<ProblemResponse> searchProblems(String keyword);

    ProblemResponse updateProblem(Long id, ProblemRequest request);

    void deleteProblem(Long id);
}