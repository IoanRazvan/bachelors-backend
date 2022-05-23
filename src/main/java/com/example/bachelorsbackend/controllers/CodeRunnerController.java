package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.dtos.SolutionImplementationDTO;
import com.example.bachelorsbackend.services.ISolutionRunnerService;
import com.example.bachelorsbackend.services.RunnerResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/code-runner")
public class CodeRunnerController {
    private final ISolutionRunnerService service;

    public CodeRunnerController(ISolutionRunnerService service) {
        this.service = service;
    }

    @PostMapping
    RunnerResult runProgram(@RequestBody SolutionImplementationDTO implementationDTO) throws IOException, InterruptedException {
        return this.service.runProgram(implementationDTO.getCode(), implementationDTO.getLangId(), implementationDTO.getInput(), implementationDTO.getOutput());
    }
}
