package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.models.ProblemContribution;
import com.example.bachelorsbackend.models.User;
import com.example.bachelorsbackend.repositories.IProblemContributionRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import static com.example.bachelorsbackend.services.ServiceUtils.getLoggedInUser;

@Service
public class ProblemContributionService implements IProblemContributionService {
    private final IProblemContributionRepository repo;

    public ProblemContributionService(IProblemContributionRepository repo) {
        this.repo = repo;
    }

    @Override
    public ProblemContribution save(ProblemContribution problemContribution) {
        User contributor = getLoggedInUser();
        problemContribution.setContributor(contributor);
        return repo.save(problemContribution);
    }
}
