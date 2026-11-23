package com.codepath.backend.service.impl;

import com.codepath.backend.dto.ProblemRequest;
import com.codepath.backend.dto.ProblemResponse;
import com.codepath.backend.entity.Problem;
import com.codepath.backend.repository.ProblemRepository;
import com.codepath.backend.service.ProblemService;
import org.springframework.stereotype.Service;
import com.codepath.backend.exception.ResourceNotFoundException;
import com.codepath.backend.exception.DuplicateResourceException;

import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemServiceImpl(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Override
    public ProblemResponse createProblem(ProblemRequest request) {

        if (problemRepository.findByProblemNumber(request.getProblemNumber()).isPresent()) {
            throw new DuplicateResourceException("Problem number already exists");
        }

        Problem problem = new Problem();
        mapRequestToEntity(request, problem);

        return mapToResponse(problemRepository.save(problem));
    }

    @Override
    public List<ProblemResponse> getAllProblems() {
        return problemRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProblemResponse getProblemById(Long id) {
        return mapToResponse(findProblem(id));
    }

    @Override
    public List<ProblemResponse> searchProblems(String keyword) {

        try {
            Integer number = Integer.valueOf(keyword);

            return problemRepository
                    .findByTitleContainingIgnoreCaseOrProblemNumber(keyword, number)
                    .stream()
                    .map(this::mapToResponse)
                    .toList();

        } catch (NumberFormatException exception) {

            return problemRepository.findByTitleContainingIgnoreCase(keyword)
                    .stream()
                    .map(this::mapToResponse)
                    .toList();
        }
    }

    @Override
    public ProblemResponse updateProblem(Long id, ProblemRequest request) {

        Problem problem = findProblem(id);

        problemRepository.findByProblemNumber(request.getProblemNumber())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Problem number already exists");
                });

        mapRequestToEntity(request, problem);

        return mapToResponse(problemRepository.save(problem));
    }

    @Override
    public void deleteProblem(Long id) {
        Problem problem = findProblem(id);
        problemRepository.delete(problem);
    }

    private Problem findProblem(Long id) {
        return problemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Problem not found with ID: " + id));
    }

    private void mapRequestToEntity(ProblemRequest request, Problem problem) {
        problem.setProblemNumber(request.getProblemNumber());
        problem.setTitle(request.getTitle());
        problem.setDifficulty(request.getDifficulty());
        problem.setDescription(request.getDescription());
        problem.setLeetcodeUrl(request.getLeetcodeUrl());
    }

    private ProblemResponse mapToResponse(Problem problem) {
        return new ProblemResponse(
                problem.getId(),
                problem.getProblemNumber(),
                problem.getTitle(),
                problem.getDifficulty(),
                problem.getDescription(),
                problem.getLeetcodeUrl(),
                problem.getCreatedAt()
        );
    }
}
