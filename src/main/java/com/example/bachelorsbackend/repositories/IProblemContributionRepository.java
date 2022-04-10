package com.example.bachelorsbackend.repositories;

import com.example.bachelorsbackend.models.ProblemContribution;
import com.example.bachelorsbackend.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;

public interface IProblemContributionRepository extends CrudRepository<ProblemContribution, Integer> {
    Slice<ProblemContribution> findByContributor(Pageable pageable, User contributor);
}
