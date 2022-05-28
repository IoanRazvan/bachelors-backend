package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.dtos.CodeDetails;
import com.example.bachelorsbackend.services.ICodeRunnerService;
import com.example.bachelorsbackend.dtos.CodeRunnerResult;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/code-runner")
public class CodeRunnerController {
    private final ICodeRunnerService service;

    public CodeRunnerController(ICodeRunnerService service) {
        this.service = service;
    }

    @PostMapping("/check-program")
    @Secured("ROLE_DEVELOPER")
    CodeRunnerResult checkProgram(@RequestBody CodeDetails codeDetails) throws IOException, InterruptedException {
        return this.service.runProgram(codeDetails);
    }

    @PostMapping("/check-against-testcases")
    @Secured("ROLE_DEVELOPER")
    List<CodeRunnerResult> checkSolutionsAgainstTestcases(@RequestBody List<CodeDetails> solutions) throws InterruptedException, ExecutionException {
        return this.service.runPrograms(solutions);
    }
}
