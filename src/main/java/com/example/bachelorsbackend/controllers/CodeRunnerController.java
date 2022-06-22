package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.dtos.coderunner.CodeDetails;
import com.example.bachelorsbackend.dtos.coderunner.CodeRunnerResult;
import com.example.bachelorsbackend.dtos.submission.SubmissionRequestDTO;
import com.example.bachelorsbackend.dtos.submission.SubmissionRowDTO;
import com.example.bachelorsbackend.services.ICodeRunnerService;
import com.example.bachelorsbackend.services.ISubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/code-runner")
public class CodeRunnerController {
    private final ICodeRunnerService service;
    private final ISubmissionService submissionService;

    public CodeRunnerController(ICodeRunnerService service, ISubmissionService submissionService) {
        this.service = service;
        this.submissionService = submissionService;
    }

    @PostMapping("/check-program")
    @Secured("ROLE_DEVELOPER")
    ResponseEntity<CodeRunnerResult> checkProgram(@RequestBody CodeDetails codeDetails) throws IOException, InterruptedException {
        return ok(service.runProgram(codeDetails));
    }

    @PostMapping("/check-against-testcases")
    @Secured("ROLE_DEVELOPER")
    ResponseEntity<List<CodeRunnerResult>> checkSolutionsAgainstTestcases(@RequestBody List<CodeDetails> solutions) throws InterruptedException, ExecutionException {
        return ok(service.runPrograms(solutions));
    }

    @PostMapping("/submit-solution/{problemId}")
    public ResponseEntity<Map<String, Object>> submitSolution(@PathVariable int problemId, @RequestBody SubmissionRequestDTO submissionDto) throws IOException, InterruptedException {
        CodeRunnerResult result = service.runSubmission(problemId, submissionDto.getSourceCode(), submissionDto.getLanguageId());
        SubmissionRowDTO submission = submissionService.save(problemId, submissionDto.getSourceCode(), result);
        return ok(Map.of("codeRunnerResult", result, "submission", submission));
    }
}
