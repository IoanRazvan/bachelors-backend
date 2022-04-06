package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.dtos.ProblemContributionDTO;
import com.example.bachelorsbackend.mappers.ProblemContributionToDTOMapper;
import com.example.bachelorsbackend.models.ProblemContribution;
import com.example.bachelorsbackend.services.IProblemContributionService;
import com.example.bachelorsbackend.dtos.Page;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/problem-contribution")
public class ProblemContributionController {
    private final IProblemContributionService service;
    private final ProblemContributionToDTOMapper mapper;

    public ProblemContributionController(IProblemContributionService service, ProblemContributionToDTOMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ProblemContributionDTO addProblemContribution(@RequestBody ProblemContributionDTO dto) {
        ProblemContribution entity = mapper.dtoToEntity(dto);
        entity = service.save(entity);
        return mapper.entityToDTO(entity);
    }

    @GetMapping
    public Page<ProblemContributionDTO> getProblemContributions(@RequestParam int page, @RequestParam int size) {
        Slice<ProblemContribution> resultsPage = service.getProblemContributions(page, size);
        return Page.of(resultsPage, mapper::entityToDTO);
    }
}
