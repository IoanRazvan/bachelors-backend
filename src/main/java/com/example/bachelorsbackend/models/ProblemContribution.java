package com.example.bachelorsbackend.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="PROBLEM_CONTRIBUTION")
@Data
public class ProblemContribution {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User contributor;

    private String title;

    private String description;

    private String solution;

    private String testcase;

    private String status;

    private String statusDetails;
}
