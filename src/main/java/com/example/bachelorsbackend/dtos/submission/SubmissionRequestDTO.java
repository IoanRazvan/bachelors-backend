package com.example.bachelorsbackend.dtos.submission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmissionRequestDTO {
    private String languageId;
    private String sourceCode;
}
