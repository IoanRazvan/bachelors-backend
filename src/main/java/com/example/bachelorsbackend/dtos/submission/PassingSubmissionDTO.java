package com.example.bachelorsbackend.dtos.submission;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PassingSubmissionDTO extends SubmissionDTO {
    private long runtime;
    List<AcceptedSubmissionDistributionBin> acceptedDistribution;
}
