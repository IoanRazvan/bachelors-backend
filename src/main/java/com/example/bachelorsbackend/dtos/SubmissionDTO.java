package com.example.bachelorsbackend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SubmissionDTO {
    private int id;
    private Date timestamp;
    private String sourceCode;
    private String languageId;
    private int statusCode;
}
