package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.models.ProblemContribution;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface IProblemContributionService {
    ProblemContribution save(ProblemContribution problemContribution);

    ProblemContribution update(int id, ProblemContribution newContribution);

    Slice<ProblemContribution> findByLoggedInUser(int page, int size);

    Optional<ProblemContribution> findById(int id);

    void deleteById(int id);

    Slice<ProblemContribution> findUnassignedContributions(int page, int size);

    void assignContribution(int contributionId);
}
