package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.dtos.*;
import com.example.bachelorsbackend.mappers.ProblemContributionMapper;
import com.example.bachelorsbackend.models.ProblemContribution;
import com.example.bachelorsbackend.services.IProblemContributionService;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Page<UnassignedContributionRowDTO>> getUnassignedContributions(@RequestParam int page, @RequestParam int size, @RequestParam(defaultValue="") String q, @RequestParam(defaultValue="descending") String order) {
        Slice<ProblemContribution> resultPage = service.findUnassignedContributions(page, size, q, order);
        return ok(PageFactory.of(resultPage, mapper::entityToUnassignedContributionRow, q, order));
    }

    @PutMapping("/assign/{id}")
    public void assignContribution(@PathVariable int id) {
        this.service.assignContribution(id);
    }

    @PutMapping("/reject/{id}")
    public void rejectContribution(@PathVariable int id, @RequestBody ContributionRefusalDTO dto) {
        this.service.rejectContribution(id, dto.getStatusDetails());
    }

    @GetMapping("/assigned")
    public ResponseEntity<Page<AssignedContributionRowDTO>> getAssignedContributions(@RequestParam int page, @RequestParam int size, @RequestParam(defaultValue="") String q, @RequestParam(defaultValue="descending") String order, @RequestParam(defaultValue="") String status) {
        Slice<ProblemContribution> resultPage = service.findAssignedContributions(page, size, q, order, status);
        return ok(PageFactory.of(resultPage, mapper::entityToAssignedContributionRow, q, order, status));
    }

    @GetMapping("/statistics")
    public ResponseEntity<List<AssignedContributionStatusCount>> getStatistics() {
        return ok(service.findDeveloperStatistics());
    }
}
