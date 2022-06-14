package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.dtos.SubmissionDTO;
import com.example.bachelorsbackend.dtos.SubmissionRowDTO;
import com.example.bachelorsbackend.services.ISubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/submission")
public class SubmissionController {
    private final ISubmissionService service;

    public SubmissionController(ISubmissionService service) {
        this.service = service;
    }

    @GetMapping("/problem/{problemId}")
    public ResponseEntity<List<SubmissionRowDTO>> getSubmissions(@PathVariable int problemId) {
        return ok(service.findSubmissions(problemId));
    }

    @GetMapping("{id}")
    public ResponseEntity<SubmissionDTO> getSubmission(@PathVariable int id) {
        SubmissionDTO submission = service.getSubmissionDetails(id);
        return ok(submission);
    }
}