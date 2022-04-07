package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.models.ProblemContribution;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface IProblemContributionService {
    ProblemContribution save(ProblemContribution problemContribution);

    Slice<ProblemContribution> findByLoggedInUser(int page, int size);

    Optional<ProblemContribution> findById(int id);
}
