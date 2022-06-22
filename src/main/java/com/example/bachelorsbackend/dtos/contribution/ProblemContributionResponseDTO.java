package com.example.bachelorsbackend.dtos.contribution;


import com.example.bachelorsbackend.models.ProblemContributionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemContributionResponseDTO extends ProblemContributionRequestDTO {
    public int id;

    public ProblemContributionStatus status;

    public String statusDetails;

    public String createdTime;
}
