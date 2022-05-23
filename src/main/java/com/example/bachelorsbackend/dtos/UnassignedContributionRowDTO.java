package com.example.bachelorsbackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnassignedContributionRowDTO {
    public int id;

    public String contributorUsername;

    public String title;

    public String createdTime;
}
