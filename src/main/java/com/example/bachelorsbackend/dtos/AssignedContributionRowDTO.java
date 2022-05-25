package com.example.bachelorsbackend.dtos;

import com.example.bachelorsbackend.models.ProblemContributionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignedContributionRowDTO extends UnassignedContributionRowDTO {
    private ProblemContributionStatus status;
}
