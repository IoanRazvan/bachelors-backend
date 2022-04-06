package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.models.ProblemContribution;
import org.springframework.data.domain.Slice;

public interface IProblemContributionService {
    ProblemContribution save(ProblemContribution problemContribution);

    Slice<ProblemContribution> getProblemContributions(int page, int size);
}
