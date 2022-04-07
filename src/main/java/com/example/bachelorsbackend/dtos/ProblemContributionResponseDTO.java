package com.example.bachelorsbackend.dtos;


import com.example.bachelorsbackend.models.ProblemContributionStatus;

public class ProblemContributionResponseDTO extends ProblemContributionRequestDTO {
    public int id;

    public ProblemContributionStatus status;

    public String statusDetails;

    public String createdTime;
}
