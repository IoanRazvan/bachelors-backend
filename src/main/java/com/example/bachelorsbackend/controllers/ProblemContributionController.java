package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.dtos.*;
import com.example.bachelorsbackend.mappers.ProblemContributionMapper;
import com.example.bachelorsbackend.models.ProblemContribution;
import com.example.bachelorsbackend.services.IProblemContributionService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.notFound;
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
    public ResponseEntity<ProblemContributionResponseDTO> save(@RequestBody ProblemContributionRequestDTO dto) {
        ProblemContribution entity = mapper.dtoToEntity(dto);
        entity = service.save(entity);
        return ok(mapper.entityToDTO(entity));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProblemContributionResponseDTO> update(@PathVariable int id, @RequestBody ProblemContributionRequestDTO dto) {
        Optional<ProblemContribution> entity = service.findById(id);
        if (entity.isEmpty())
            return notFound().build();
        ProblemContribution merged = new ProblemContribution(entity.get());
        BeanUtils.copyProperties(dto, merged);
        ProblemContribution updated = service.update(id, merged);
        return ok(mapper.entityToDTO(updated));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    @GetMapping
    public ResponseEntity<Page<PreviousContributionRowDTO>> getContributions(@RequestParam int page, @RequestParam int size) {
        Slice<ProblemContribution> resultsPage = service.findByLoggedInUser(page, size);
        return ok(PageFactory.of(resultsPage, mapper::entityToPreviousContributionRow));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProblemContributionResponseDTO> getContribution(@PathVariable int id) {
        Optional<ProblemContribution> contribution = service.findById(id);
        if (contribution.isEmpty())
            return ResponseEntity.notFound().build();
        return ok(mapper.entityToDTO(contribution.get()));
    }
}
