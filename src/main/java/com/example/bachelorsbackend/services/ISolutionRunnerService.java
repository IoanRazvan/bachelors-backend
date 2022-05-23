package com.example.bachelorsbackend.services;

import java.io.IOException;

public interface ISolutionRunnerService {
    RunnerResult runProgram(String code, int langId, String input, String output) throws IOException, InterruptedException;
}
