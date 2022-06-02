package com.example.bachelorsbackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FailedSubmissionDTO {
    private String error;
    private String input;
    private String output;
    private String expected;
}
