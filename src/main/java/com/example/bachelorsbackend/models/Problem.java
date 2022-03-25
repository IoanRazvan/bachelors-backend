package com.example.bachelorsbackend.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Problem {
    @Id
    private int id;

    private String problemTitle;

    private String problemStatement;

    @ManyToOne
    private User author;

    private int numberOfLikes;

    private int numberOfDislikes;

    private String problemInput;

    private boolean isPublic;

    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY)
    private List<ProblemDetail> problemDetails;

    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY)
    private List<ProblemSolution> problemSolutions;

    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY)
    private List<ProblemCategory> problemCategories;
}
