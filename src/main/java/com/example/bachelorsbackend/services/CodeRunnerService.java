package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.dtos.CodeDetails;
import com.example.bachelorsbackend.dtos.CodeRunnerResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class CodeRunnerService implements ICodeRunnerService {
    private ObjectMapper mapper;
    private ExecutorService executorService;

    public CodeRunnerService() {
        mapper = new ObjectMapper();
        executorService = Executors.newFixedThreadPool(9);
    }

    @Override
    public CodeRunnerResult runProgram(CodeDetails codeDetails) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("docker", "run", "-i","--rm", "img");
        Process p = pb.start();

        BufferedWriter stdIn = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
        BufferedReader stdOut = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String json = mapper.writeValueAsString(codeDetails);
        stdIn.write(json);
        stdIn.flush();
        p.waitFor();
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = stdOut.readLine()) != null)
            result.append(line).append("\n");
        CodeRunnerResult codeRunnerResult = mapper.readValue(result.toString(), CodeRunnerResult.class);
        stdIn.close();
        stdOut.close();
        return codeRunnerResult;
    }

    @Override
    public List<CodeRunnerResult> runPrograms(List<CodeDetails> codeDetailsList) throws InterruptedException, ExecutionException {
        List<Future<CodeRunnerResult>> futures = new ArrayList<>();
        List<CodeRunnerResult> results = new ArrayList<>();

        for (CodeDetails codeDetails : codeDetailsList)
            futures.add(executorService.submit(() -> runProgram(codeDetails)));

        for (Future<CodeRunnerResult> future : futures)
            results.add(future.get());
        return results;
    }
}
