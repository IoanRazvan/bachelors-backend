package com.example.bachelorsbackend.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProblemContributionDTO {
    private int id;

    private String title;

    private String description;

    private String solution;

    private String testcase;

    private String status;

    private String statusDetails;

    private LocalDateTime createdTime;
}
