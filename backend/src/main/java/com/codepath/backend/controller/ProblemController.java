package com.codepath.backend.controller;

import com.codepath.backend.dto.ProblemRequest;
import com.codepath.backend.dto.ProblemResponse;
import com.codepath.backend.service.ProblemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {

    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProblemResponse createProblem(
            @Valid @RequestBody ProblemRequest request) {
        return problemService.createProblem(request);
    }

    @GetMapping
    public List<ProblemResponse> getAllProblems() {
        return problemService.getAllProblems();
    }

    @GetMapping("/{id}")
    public ProblemResponse getProblemById(@PathVariable Long id) {
        return problemService.getProblemById(id);
    }

    @GetMapping("/search")
    public List<ProblemResponse> searchProblems(
            @RequestParam String keyword) {
        return problemService.searchProblems(keyword);
    }

    @PutMapping("/{id}")
    public ProblemResponse updateProblem(
            @PathVariable Long id,
            @Valid @RequestBody ProblemRequest request) {
        return problemService.updateProblem(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProblem(@PathVariable Long id) {
        problemService.deleteProblem(id);
    }
}