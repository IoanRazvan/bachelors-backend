package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.dtos.coderunner.CodeDetails;
import com.example.bachelorsbackend.dtos.coderunner.CodeRunnerResult;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ICodeRunnerService {
    CodeRunnerResult runProgram(CodeDetails codeDetails) throws IOException, InterruptedException;
    List<CodeRunnerResult> runPrograms(List<CodeDetails> codeDetailsList) throws InterruptedException, ExecutionException;
    CodeRunnerResult runSubmission(int problemId, String sourceCode, String languageId) throws IOException, InterruptedException;
}
