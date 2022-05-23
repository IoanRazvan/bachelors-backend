package com.example.bachelorsbackend.services;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class SolutionRunnerService implements ISolutionRunnerService {
    @Override
    public RunnerResult runProgram(String code, int langId, String input, String output) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("docker", "run", "--rm", "-i", "img");
        pb.redirectErrorStream(true);
        Process p = pb.start();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

        bw.write(Integer.toString(langId));
        bw.write(code);
        bw.write("#\n");
        bw.write(input);
        bw.close();
//        int status = p.waitFor();
        StringBuilder builder = new StringBuilder();
        String line;
        int i = 0;
        String[] outputLine = output.split("\n");
        while ((line = br.readLine()) != null) {
            if (!line.equals(outputLine[i])) {
                builder.append("Expected: ").append(outputLine[i]).append("Actual: ").append(line);
            }
//            builder.append(line).append('\n');
        }
        int status = p.waitFor();
        return new RunnerResult(status, builder.toString());
    }
}
