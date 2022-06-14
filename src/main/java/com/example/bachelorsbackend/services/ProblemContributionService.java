package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.dtos.AssignedContributionRowDTO;
import com.example.bachelorsbackend.dtos.AssignedContributionStatusCount;
import com.example.bachelorsbackend.dtos.ProblemRequestDTO;
import com.example.bachelorsbackend.dtos.UnassignedContributionRowDTO;
import com.example.bachelorsbackend.mappers.ProblemContributionMapper;
import com.example.bachelorsbackend.mappers.ProblemMapper;
import com.example.bachelorsbackend.models.ProblemContribution;
import com.example.bachelorsbackend.models.ProblemContributionStatus;
import com.example.bachelorsbackend.models.User;
import com.example.bachelorsbackend.repositories.IProblemContributionRepository;
import com.example.bachelorsbackend.security.UserJwtAuthenticationToken;
import com.example.bachelorsbackend.services.exceptions.AccessDeniedException;
import com.example.bachelorsbackend.services.exceptions.InvalidOperationException;
import com.example.bachelorsbackend.services.exceptions.ResourceNotFoundException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.StaleStateException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static com.example.bachelorsbackend.services.ServiceUtils.*;

@Service
public class ProblemContributionService implements IProblemContributionService {
    private final IProblemContributionRepository repo;
    private final IProblemService service;
    private final Converter<List<Object[]>, List<AssignedContributionStatusCount>> converter;
    private final ProblemContributionMapper contributionMapper;
    private final ProblemMapper problemMapper;
    public static final String UPDATE_DIFFERENT_ENTITY_IDS_ERROR = "ID of old contribution must match that of new contribution";
    public static final String UPDATE_NON_PENDING_CONTRIBUTION_ERROR = "Only pending contributions can be updated";
    public static final String UPDATE_READONLY_FIELD_ERROR = "Tried to update read only field";
    public static final String DELETE_NON_PENDING_CONTRIBUTION_ERROR = "Only pending contributions can be deleted";
    public static final String UPDATE_CONTRIBUTION_ASSIGNED_TO = "Contributions cannot be reassigned";
    public static final String UPDATE_NON_ASSIGNED_CONTRIBUTION = "Only assigned contributions can be rejected";


    public ProblemContributionService(IProblemContributionRepository repo, IProblemService service, Converter<List<Object[]>, List<AssignedContributionStatusCount>> converter, ProblemContributionMapper contributionMapper, ProblemMapper problemMapper) {
        this.repo = repo;
        this.service = service;
        this.converter = converter;
        this.contributionMapper = contributionMapper;
        this.problemMapper = problemMapper;
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

        UserJwtAuthenticationToken authentication = getAuthentication();
        User user = getLoggedInUser();
        if (oldContribution.getContributor().equals(user))
            checkUpdateByOwner(oldContribution, newContribution);
        else if (hasDeveloperRole(authentication) && (oldContribution.getAssignedTo() == null || oldContribution.getAssignedTo().equals(user)))
            checkUpdateByDeveloper(oldContribution, newContribution);
        else
            throw new AccessDeniedException();
        return repo.save(newContribution);
    }

    private void checkUpdateByDeveloper(ProblemContribution oldContribution, ProblemContribution newContribution) {
        boolean equals = EqualsBuilder.reflectionEquals(oldContribution, newContribution, "status", "statusDetails");
        if (!equals)
            throw new InvalidOperationException(UPDATE_READONLY_FIELD_ERROR);
    }

    private void checkUpdateByOwner(ProblemContribution oldContribution, ProblemContribution newContribution) {
        boolean equals = EqualsBuilder.reflectionEquals(oldContribution, newContribution, "title", "description", "solution", "testcase");
        if (!equals)
            throw new InvalidOperationException(UPDATE_READONLY_FIELD_ERROR);
    }

    @Override
    public Slice<ProblemContribution> findByLoggedInUser(int page, int size) {
        User contributor = getLoggedInUser();
        return repo.findByContributor(PageRequest.of(page, size, Sort.Direction.DESC, "createdTime"), contributor);
    }

    @Override
    public Optional<ProblemContribution> findById(int id) {
        Optional<ProblemContribution> contributionOpt = repo.findById(id);
        contributionOpt.ifPresent(contribution -> {
            UserJwtAuthenticationToken authentication = getAuthentication();
            User user = getLoggedInUser();
            if (!contribution.getContributor().equals(user) && !hasDeveloperRole(authentication))
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
        }, ResourceNotFoundException::new);
    }

    @Override
    public Slice<UnassignedContributionRowDTO> findUnassignedContributions(int page, int size, String q, String order) {
        User user = getLoggedInUser();
        UserJwtAuthenticationToken authentication = getAuthentication();
        if (!hasDeveloperRole(authentication))
            throw new AccessDeniedException();
        Sort.Direction sortingDirection = convertStringToOrderDirection(order);
        Slice<ProblemContribution> result = repo.findUnassignedContributions(PageRequest.of(page, size, Sort.by(sortingDirection, "createdTime")), user, q);
        return result.map(contributionMapper::entityToUnassignedContributionRow);
    }

    private Sort.Direction convertStringToOrderDirection(String order) {
        if (order.equals("ascending"))
            return Sort.Direction.ASC;
        return Sort.Direction.DESC;
    }

    @Override
    public Slice<AssignedContributionRowDTO> findAssignedContributions(int page, int size, String q, String order, String status) {
        User user = getLoggedInUser();
        UserJwtAuthenticationToken authentication = getAuthentication();
        if (!hasDeveloperRole(authentication))
            throw new AccessDeniedException();
        List<ProblemContributionStatus> statuses = convertStringToProblemContributionStatusList(status);
        Sort.Direction sortingDirection = convertStringToOrderDirection(order);
        Slice<ProblemContribution> result = repo.findAssignedContributions(PageRequest.of(page, size, Sort.by(sortingDirection, "createdTime")), user, statuses, q);
        return result.map(contributionMapper::entityToAssignedContributionRow);
    }

    private List<ProblemContributionStatus> convertStringToProblemContributionStatusList(String status) {
        if (status.equals(""))
            return List.of(ProblemContributionStatus.PENDING, ProblemContributionStatus.REJECTED, ProblemContributionStatus.ACCEPTED);
        return List.of(ProblemContributionStatus.valueOf(status));
    }


    @Override
    public void assignContribution(int contributionId) {
        User u = getLoggedInUser();
        UserJwtAuthenticationToken authentication = getAuthentication();
        if (!hasDeveloperRole(authentication))
            throw new AccessDeniedException();
        Optional<ProblemContribution> contributionOptional = repo.findById(contributionId);
        contributionOptional.ifPresentOrElse((contribution) -> {
            if (contribution.getAssignedTo() == null)
                assignContributionHelper(contribution, u);
            else
                throw new InvalidOperationException(UPDATE_CONTRIBUTION_ASSIGNED_TO);
        }, ResourceNotFoundException::new);
    }

    private void assignContributionHelper(ProblemContribution contribution, User assignedTo) {
        contribution.setAssignedTo(assignedTo);
        try {
            repo.save(contribution);
        } catch (StaleStateException ex) {
            throw new InvalidOperationException(UPDATE_CONTRIBUTION_ASSIGNED_TO);
        }
    }

    @Override
    public void rejectContribution(int contributionId, String statusDetails) {
        setContributionStatus(contributionId, ProblemContributionStatus.REJECTED, (contribution) -> contribution.setStatusDetails(statusDetails));
    }

    @Override
    @Transactional
    public void acceptContribution(int contributionId, ProblemRequestDTO problem) {
        setContributionStatus(contributionId, ProblemContributionStatus.ACCEPTED, (contribution) -> {
        });
        service.save(problemMapper.problemDtoToEntity(problem));
    }

    private void setContributionStatus(int contributionId, ProblemContributionStatus status, Consumer<ProblemContribution> additionalBehavior) {
        User u = getLoggedInUser();
        UserJwtAuthenticationToken authentication = getAuthentication();
        if (!hasDeveloperRole(authentication))
            throw new AccessDeniedException();
        Optional<ProblemContribution> contributionOptional = repo.findById(contributionId);
        contributionOptional.ifPresentOrElse((contribution) -> {
            if (contribution.getAssignedTo() == null || !contribution.getAssignedTo().equals(u))
                throw new InvalidOperationException(UPDATE_NON_ASSIGNED_CONTRIBUTION);
            else if (contribution.getStatus() != ProblemContributionStatus.PENDING)
                throw new InvalidOperationException(UPDATE_NON_PENDING_CONTRIBUTION_ERROR);
            contribution.setStatus(status);
            additionalBehavior.accept(contribution);
            repo.save(contribution);
        }, ResourceNotFoundException::new);
    }

    @Override
    public List<AssignedContributionStatusCount> findDeveloperStatistics() {
        User developer = getLoggedInUser();
        UserJwtAuthenticationToken authentication = getAuthentication();
        if (!hasDeveloperRole(authentication))
            throw new AccessDeniedException();
        List<Object[]> statistics = repo.findDeveloperStatistics(developer);
        return converter.convert(statistics);
    }
}
