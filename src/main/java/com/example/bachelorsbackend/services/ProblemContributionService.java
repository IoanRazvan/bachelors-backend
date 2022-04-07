package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.models.ProblemContribution;
import com.example.bachelorsbackend.models.User;
import com.example.bachelorsbackend.models.UserRole;
import com.example.bachelorsbackend.repositories.IProblemContributionRepository;
import com.example.bachelorsbackend.services.exceptions.AccessDeniedException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Slice<ProblemContribution> findByLoggedInUser(int page, int size) {
        User contributor = getLoggedInUser();
        return repo.findByContributorId(PageRequest.of(page, size, Sort.Direction.DESC, "createdTime"), contributor.getId());
    }

    @Override
    public Optional<ProblemContribution> findById(int id) {
        Optional<ProblemContribution> contributionOpt = repo.findById(id);
        contributionOpt.ifPresent(contribution -> {
            User user = getLoggedInUser();
            if (!contribution.getContributor().equals(user) && user.getRole() != UserRole.ROLE_DEVELOPER)
                throw new AccessDeniedException();
        });
        return contributionOpt;
    }
}
