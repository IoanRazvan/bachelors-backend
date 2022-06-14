package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.dtos.*;
import com.example.bachelorsbackend.services.IProblemContributionService;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/manage-contributions")
@Secured("ROLE_DEVELOPER")
public class ManageContributionsController {
    IProblemContributionService contributionService;


    public ManageContributionsController(IProblemContributionService contributionService) {
        this.contributionService = contributionService;
    }

    @GetMapping("/unassigned")
    public ResponseEntity<Page<UnassignedContributionRowDTO>> getUnassignedContributions(@RequestParam int page, @RequestParam int size, @RequestParam(defaultValue = "") String q, @RequestParam(defaultValue = "descending") String order) {
        Slice<UnassignedContributionRowDTO> resultPage = contributionService.findUnassignedContributions(page, size, q, order);
        Map<String, Object> parameters = Map.of("query", q, "order", order);
        return ok(PageFactory.of(resultPage, parameters));
    }

    @PutMapping("/assign/{id}")
    public void assignContribution(@PathVariable int id) {
        contributionService.assignContribution(id);
    }

    @PutMapping("/reject/{id}")
    public void rejectContribution(@PathVariable int id, @RequestBody ContributionRefusalDTO dto) {
        contributionService.rejectContribution(id, dto.getStatusDetails());
    }

    @PutMapping("/accept/{id}")
    public void acceptContribution(@PathVariable int id, @RequestBody ProblemRequestDTO dto) {
        contributionService.acceptContribution(id, dto);
    }

    @GetMapping("/assigned")
    public ResponseEntity<Page<AssignedContributionRowDTO>> getAssignedContributions(@RequestParam int page, @RequestParam int size, @RequestParam(defaultValue="") String q, @RequestParam(defaultValue="descending") String order, @RequestParam(defaultValue="") String status) {
        Slice<AssignedContributionRowDTO> resultPage = contributionService.findAssignedContributions(page, size, q, order, status);
        Map<String, Object> parameters = Map.of("query", q, "order", order, "status", status);
        return ok(PageFactory.of(resultPage, parameters));
    }

    @GetMapping("/statistics")
    public ResponseEntity<List<AssignedContributionStatusCount>> getStatistics() {
        return ok(contributionService.findDeveloperStatistics());
    }
}
