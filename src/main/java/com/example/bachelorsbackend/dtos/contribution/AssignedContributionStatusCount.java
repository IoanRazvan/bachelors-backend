package com.example.bachelorsbackend.dtos.contribution;

import com.example.bachelorsbackend.models.ProblemContributionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AssignedContributionStatusCount {
    private ProblemContributionStatus status;
    private long count;
}
