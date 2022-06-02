package com.example.bachelorsbackend.repositories;

import com.example.bachelorsbackend.models.Submission;
import com.example.bachelorsbackend.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ISubmissionRepository extends CrudRepository<Submission, Integer> {
    List<Submission> findByUserAndProblemId(Sort sort, User user, int problemId);
}
