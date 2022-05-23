package com.example.bachelorsbackend.repositories;

import com.example.bachelorsbackend.models.ProblemContribution;
import com.example.bachelorsbackend.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IProblemContributionRepository extends CrudRepository<ProblemContribution, Integer> {
    Slice<ProblemContribution> findByContributor(Pageable pageable, User contributor);
    @Query("select pc from ProblemContribution pc where pc.contributor <> ?1 and pc.status = 'PENDING' and pc.assignedTo is null")
    Slice<ProblemContribution> findUnassignedContributions(Pageable pageable, User contributor);
}
