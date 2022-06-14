package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.dtos.*;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface IProblemContributionService {
    ProblemContributionResponseDTO save(ProblemContributionRequestDTO problemContribution);

    ProblemContributionResponseDTO update(int id, ProblemContributionRequestDTO newContribution);

    Slice<PreviousContributionRowDTO> findByLoggedInUser(int page, int size);

    ProblemContributionResponseDTO findById(int id);

    void deleteById(int id);

    Slice<UnassignedContributionRowDTO> findUnassignedContributions(int page, int size, String q, String order);

    Slice<AssignedContributionRowDTO> findAssignedContributions(int page, int size, String q, String order, String status);

    void assignContribution(int contributionId);

    void rejectContribution(int contributionId, String statusDetails);

    void acceptContribution(int contributionId, ProblemRequestDTO problem);

    List<AssignedContributionStatusCount> findDeveloperStatistics();
}
