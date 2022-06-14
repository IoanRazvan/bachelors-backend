package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.dtos.CodeRunnerResult;
import com.example.bachelorsbackend.dtos.SubmissionDTO;
import com.example.bachelorsbackend.dtos.SubmissionRowDTO;

import java.util.List;

public interface ISubmissionService {
    List<SubmissionRowDTO> findSubmissions(int problemId);

    SubmissionRowDTO save(int problemId, String sourceCode, CodeRunnerResult result);

    SubmissionDTO getSubmissionDetails(int id);
}
