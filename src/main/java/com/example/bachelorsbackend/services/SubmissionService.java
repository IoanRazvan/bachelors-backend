package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.dtos.CodeRunnerResult;
import com.example.bachelorsbackend.models.Submission;
import com.example.bachelorsbackend.models.User;
import com.example.bachelorsbackend.repositories.ISubmissionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.bachelorsbackend.services.ServiceUtils.getLoggedInUser;

@Service
public class SubmissionService implements ISubmissionService {
    private final ISubmissionRepository repo;

    public SubmissionService(ISubmissionRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Submission> findSubmissions(int problemId) {
        User user = getLoggedInUser();
        return repo.findByUserAndProblemId(Sort.by(Sort.Direction.DESC, "timestamp"), user, problemId);
    }

    @Override
    public Submission save(int problemId, String sourceCode, CodeRunnerResult result) {
        User user = getLoggedInUser();
        Submission submission = new Submission(user, problemId, sourceCode, result);
        return repo.save(submission);
    }
}
