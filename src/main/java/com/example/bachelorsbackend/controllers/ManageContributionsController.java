package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.dtos.ContributionRefusalDTO;
import com.example.bachelorsbackend.dtos.Page;
import com.example.bachelorsbackend.dtos.UnassignedContributionRowDTO;
import com.example.bachelorsbackend.mappers.ProblemContributionMapper;
import com.example.bachelorsbackend.models.ProblemContribution;
import com.example.bachelorsbackend.services.IProblemContributionService;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/manage-contributions")
@Secured("ROLE_DEVELOPER")
public class ManageContributionsController {
    IProblemContributionService service;
    ProblemContributionMapper mapper;

    public ManageContributionsController(IProblemContributionService service, ProblemContributionMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/unassigned")
    public ResponseEntity<Page<UnassignedContributionRowDTO>> getUnassignedContributions(@RequestParam int page, @RequestParam int size) {
        Slice<ProblemContribution> resultPage = service.findUnassignedContributions(page, size);
        return ok(Page.of(resultPage, mapper::entityToUnassignedContributionRow));
    }

    @PutMapping("/assign/{id}")
    public void assignContribution(@PathVariable int id) {
        this.service.assignContribution(id);
    }

    @PutMapping("/reject/{id}")
    public void rejectContribution(@PathVariable int id, @RequestBody ContributionRefusalDTO dto) {
        this.service.rejectContribution(id, dto.getStatusDetails());
    }
}
