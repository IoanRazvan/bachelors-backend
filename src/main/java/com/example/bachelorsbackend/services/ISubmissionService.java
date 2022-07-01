package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.dtos.coderunner.CodeRunnerResult;
import com.example.bachelorsbackend.dtos.submission.SubmissionDTO;
import com.example.bachelorsbackend.dtos.submission.SubmissionDateCountDTO;
import com.example.bachelorsbackend.dtos.submission.SubmissionRowDTO;

import java.util.List;

public interface ISubmissionService {
    List<SubmissionRowDTO> findSubmissions(int problemId);

    SubmissionRowDTO save(int problemId, String sourceCode, CodeRunnerResult result);

    SubmissionDTO getSubmissionDetails(int id);

    List<SubmissionDateCountDTO> getSubmissionsDateCount();
}
