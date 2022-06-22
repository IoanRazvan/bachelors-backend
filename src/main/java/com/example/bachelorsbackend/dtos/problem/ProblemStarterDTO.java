package com.example.bachelorsbackend.dtos.problem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemStarterDTO {
    private String languageId;
    private String languageName;
    private String sourceCode;
}
