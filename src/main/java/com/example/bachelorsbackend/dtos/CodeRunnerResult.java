package com.example.bachelorsbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CodeRunnerResult {
    private int status;
    private String output;
    private String error;
    private String stdout;
    private String langId;
    private long runtime;
    private WrongAnswer wrongAnswer;
}
