package com.example.bachelorsbackend.dtos.contribution;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreviousContributionRowDTO {
    public int id;

    public String status;

    public String title;

    public String createdTime;
}
