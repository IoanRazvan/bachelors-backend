package com.example.bachelorsbackend.repositories;

import com.example.bachelorsbackend.models.ProblemContribution;
import org.springframework.data.repository.CrudRepository;

public interface IProblemContributionRepository extends CrudRepository<ProblemContribution, Integer> {
}
