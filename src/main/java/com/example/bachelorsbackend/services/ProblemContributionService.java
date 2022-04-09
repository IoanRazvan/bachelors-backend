package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.models.ProblemContribution;
import com.example.bachelorsbackend.models.ProblemContributionStatus;
import com.example.bachelorsbackend.models.User;
import com.example.bachelorsbackend.models.UserRole;
import com.example.bachelorsbackend.repositories.IProblemContributionRepository;
import com.example.bachelorsbackend.services.exceptions.AccessDeniedException;
import com.example.bachelorsbackend.services.exceptions.InvalidOperationException;
import com.example.bachelorsbackend.services.exceptions.ResourceNotFoundException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.bachelorsbackend.services.ServiceUtils.getLoggedInUser;

@Service
public class ProblemContributionService implements IProblemContributionService {
    private final IProblemContributionRepository repo;
    public static final String UPDATE_DIFFERENT_ENTITY_IDS_ERROR = "ID of old contribution must match that of new contribution";
    public static final String UPDATE_NON_PENDING_CONTRIBUTION_ERROR = "Only pending contributions can be updated";
    public static final String UPDATE_READONLY_FIELD_ERROR = "Tried to update read only field";
    public static final String DELETE_NON_PENDING_CONTRIBUTION_ERROR = "Only pending contributions can be deleted";



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
    public ProblemContribution update(int id, ProblemContribution newContribution) {
        ProblemContribution oldContribution = repo.findById(id).orElseThrow(ResourceNotFoundException::new);
        if (oldContribution.getId() != newContribution.getId())
            throw new IllegalArgumentException(UPDATE_DIFFERENT_ENTITY_IDS_ERROR);
        if (oldContribution.getStatus() != ProblemContributionStatus.PENDING)
            throw new InvalidOperationException(UPDATE_NON_PENDING_CONTRIBUTION_ERROR);

        User user = getLoggedInUser();
        if (user.getRole() == UserRole.ROLE_DEVELOPER)
            checkUpdateByDeveloper(oldContribution, newContribution);
        else
            checkUpdateByNormalUser(oldContribution, newContribution);
        return repo.save(newContribution);
    }

    private void checkUpdateByDeveloper(ProblemContribution oldContribution, ProblemContribution newContribution) {
        boolean equals = EqualsBuilder.reflectionEquals(oldContribution, newContribution, "status", "statusDetails");
        if (!equals)
            throw new InvalidOperationException(UPDATE_READONLY_FIELD_ERROR);
    }

    private void checkUpdateByNormalUser(ProblemContribution oldContribution, ProblemContribution newContribution) {
        boolean equals = EqualsBuilder.reflectionEquals(oldContribution, newContribution, "title", "description", "solution", "testcase");
        if (!equals)
            throw new InvalidOperationException(UPDATE_READONLY_FIELD_ERROR);
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

    @Override
    public void deleteById(int id) {
        Optional<ProblemContribution> contributionOpt = repo.findById(id);
        contributionOpt.ifPresentOrElse(contribution -> {
            User user = getLoggedInUser();
            if (!contribution.getContributor().equals(user))
                throw new AccessDeniedException();
            if (contribution.getStatus() != ProblemContributionStatus.PENDING)
                throw new InvalidOperationException(DELETE_NON_PENDING_CONTRIBUTION_ERROR);
            this.repo.delete(contribution);
        }, () -> {
            throw new ResourceNotFoundException();
        });
    }
}
