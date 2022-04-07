package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.dtos.Page;
import com.example.bachelorsbackend.dtos.ProblemContributionRequestDTO;
import com.example.bachelorsbackend.dtos.ProblemContributionResponseDTO;
import com.example.bachelorsbackend.mappers.ProblemContributionMapper;
import com.example.bachelorsbackend.models.ProblemContribution;
import com.example.bachelorsbackend.services.IProblemContributionService;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/problem-contribution")
public class ProblemContributionController {
    private final IProblemContributionService service;
    private final ProblemContributionMapper mapper;

    public ProblemContributionController(IProblemContributionService service, ProblemContributionMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ProblemContributionResponseDTO> addProblemContribution(@RequestBody ProblemContributionRequestDTO dto) {
        ProblemContribution entity = mapper.dtoToEntity(dto);
        entity = service.save(entity);
        return ok(mapper.entityToDTO(entity));
    }

    @GetMapping
    public ResponseEntity<Page<ProblemContributionResponseDTO>> getProblemContributions(@RequestParam int page, @RequestParam int size) {
        Slice<ProblemContribution> resultsPage = service.findByLoggedInUser(page, size);
        return ok(Page.of(resultsPage, mapper::entityToDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProblemContributionResponseDTO> getProblem(@PathVariable int id) {
        Optional<ProblemContribution> contribution = service.findById(id);
        if (contribution.isEmpty())
            return ResponseEntity.notFound().build();
        return ok(mapper.entityToDTO(contribution.get()));
    }
}
