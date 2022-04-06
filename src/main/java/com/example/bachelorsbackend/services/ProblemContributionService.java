package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.models.ProblemContribution;
import com.example.bachelorsbackend.models.User;
import com.example.bachelorsbackend.repositories.IProblemContributionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
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

    @Override
    public Slice<ProblemContribution> getProblemContributions(int page, int size) {
        User contributor = getLoggedInUser();
        return repo.findByContributorId(PageRequest.of(page, size, Sort.Direction.DESC, "createdTime"), contributor.getId());
    }
}
