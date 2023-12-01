package com.example.bachelorsbackend.repositories;

import com.example.bachelorsbackend.models.ProblemContribution;
import com.example.bachelorsbackend.models.ProblemContributionStatus;
import com.example.bachelorsbackend.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface IProblemContributionRepository extends CrudRepository<ProblemContribution, Integer> {
    Slice<ProblemContribution> findByContributor(Pageable pageable, User contributor);
    @Query("select pc from ProblemContribution pc where pc.contributor <> ?1 and pc.status = 'PENDING' and pc.assignedTo is null and lower(pc.title) like concat('%', lower(?2), '%')")
    Slice<ProblemContribution> findUnassignedContributions(Pageable pageable, User contributor, String query);
    @Query("select pc from ProblemContribution pc where pc.assignedTo = ?1 and pc.status in ?2 and lower(pc.title) like concat('%', lower(?3), '%') ")
    Slice<ProblemContribution> findAssignedContributions(Pageable pageable, User assignedTo, List<ProblemContributionStatus> status, String query);
    @Query("select pc.status, count(pc.status) from ProblemContribution pc where pc.assignedTo = ?1 group by pc.status")
    List<Object[]> findDeveloperStatistics(User developer);
}
