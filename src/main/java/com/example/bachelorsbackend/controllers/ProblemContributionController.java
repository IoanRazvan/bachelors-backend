package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.dtos.*;
import com.example.bachelorsbackend.services.IProblemContributionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/problem-contribution")
public class ProblemContributionController {
    private final IProblemContributionService service;

    public ProblemContributionController(IProblemContributionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProblemContributionResponseDTO> save(@RequestBody ProblemContributionRequestDTO dto) {
        return ok(service.save(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProblemContributionResponseDTO> update(@PathVariable int id, @RequestBody ProblemContributionRequestDTO dto) {
        return ok(service.update(id, dto));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    @GetMapping
    public ResponseEntity<Page<PreviousContributionRowDTO>> getContributions(@RequestParam int page, @RequestParam int size) {
        return ok(PageFactory.of(service.findByLoggedInUser(page, size)));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProblemContributionResponseDTO> getContribution(@PathVariable int id) {
        return ok(service.findById(id));
    }
}
