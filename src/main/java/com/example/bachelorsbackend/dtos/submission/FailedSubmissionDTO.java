package com.example.bachelorsbackend.dtos.submission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FailedSubmissionDTO extends SubmissionDTO {
    private String error;
    private String input;
    private String output;
    private String expected;
}
