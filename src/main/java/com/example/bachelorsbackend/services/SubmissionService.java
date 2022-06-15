package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.dtos.CodeRunnerResult;
import com.example.bachelorsbackend.dtos.SubmissionDTO;
import com.example.bachelorsbackend.dtos.SubmissionRowDTO;
import com.example.bachelorsbackend.mappers.SubmissionMapper;
import com.example.bachelorsbackend.models.Submission;
import com.example.bachelorsbackend.models.User;
import com.example.bachelorsbackend.repositories.ISubmissionRepository;
import com.example.bachelorsbackend.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.bachelorsbackend.services.ServiceUtils.getLoggedInUser;

@Service
public class SubmissionService implements ISubmissionService {
    private final ISubmissionRepository repo;
    private final SubmissionMapper mapper;

    public SubmissionService(ISubmissionRepository repo, SubmissionMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public List<SubmissionRowDTO> findSubmissions(int problemId) {
        User user = getLoggedInUser();
        List<Submission> result = repo.findByUserAndProblemId(Sort.by(Sort.Direction.DESC, "timestamp"), user, problemId);
        return result.stream().map(mapper::submissionEntityToRowDto).collect(Collectors.toList());
    }

    @Override
    public SubmissionRowDTO save(int problemId, String sourceCode, CodeRunnerResult result) {
        User user = getLoggedInUser();
        Submission submission = new Submission(user, problemId, sourceCode, result);
        return mapper.submissionEntityToRowDto(repo.save(submission));
    }

    @Override
    public SubmissionDTO getSubmissionDetails(int id) {
        Submission s = repo.findById(id).orElseThrow(ResourceNotFoundException::new);
        if (s.getStatusCode() == 0) {
            List<Object[]> bins = repo.getDistribution(s.getProblem().getId(), s.getProgrammingLanguage().getId());
            return mapper.passingSubmissionEntityToDto(s, bins);
        }
        return mapper.failedSubmissionEntityToDto(s);
    }
}
