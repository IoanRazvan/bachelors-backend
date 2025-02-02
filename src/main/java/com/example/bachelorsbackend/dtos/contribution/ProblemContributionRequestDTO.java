package com.example.bachelorsbackend.dtos.contribution;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemContributionRequestDTO {
    public String title;

    public String description;

    public String solution;

    public String testcase;
}
