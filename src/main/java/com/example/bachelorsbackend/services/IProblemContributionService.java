package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.dtos.AssignedContributionRowDTO;
import com.example.bachelorsbackend.dtos.AssignedContributionStatusCount;
import com.example.bachelorsbackend.dtos.ProblemRequestDTO;
import com.example.bachelorsbackend.dtos.UnassignedContributionRowDTO;
import com.example.bachelorsbackend.models.ProblemContribution;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface IProblemContributionService {
    ProblemContribution save(ProblemContribution problemContribution);

    ProblemContribution update(int id, ProblemContribution newContribution);

    Slice<ProblemContribution> findByLoggedInUser(int page, int size);

    Optional<ProblemContribution> findById(int id);

    void deleteById(int id);

    Slice<UnassignedContributionRowDTO> findUnassignedContributions(int page, int size, String q, String order);

    Slice<AssignedContributionRowDTO> findAssignedContributions(int page, int size, String q, String order, String status);

    void assignContribution(int contributionId);

    void rejectContribution(int contributionId, String statusDetails);

    void acceptContribution(int contributionId, ProblemRequestDTO problem);

    List<AssignedContributionStatusCount> findDeveloperStatistics();
}
