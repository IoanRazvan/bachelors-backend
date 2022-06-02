package com.example.bachelorsbackend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SubmissionRowDTO {
    private int id;
    private Date timestamp;
    private int statusCode;
    private long runtime;
    private String languageId;
}
