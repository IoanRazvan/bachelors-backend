package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.dtos.ProblemContributionDTO;
import com.example.bachelorsbackend.mappers.ProblemContributionToDTOMapper;
import com.example.bachelorsbackend.models.ProblemContribution;
import com.example.bachelorsbackend.services.IProblemContributionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
