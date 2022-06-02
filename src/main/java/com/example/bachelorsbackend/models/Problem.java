package com.example.bachelorsbackend.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Problem {
    @Id
    @GeneratedValue
    private int id;

    private String title;

    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    private ProblemDifficulty difficulty;

    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProblemTestcase> problemTestcases;

    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProblemSolution> problemSolutions;

    @JoinTable(name="PROBLEM_CATEGORY")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Category> problemCategories;

    public void setProblemSolutions(List<ProblemSolution> problemSolutions) {
        for (ProblemSolution solution : problemSolutions)
            solution.setProblem(this);
        this.problemSolutions = problemSolutions;
    }

    public void setProblemTestcases(List<ProblemTestcase> testcases) {
        for (ProblemTestcase testcase : testcases)
            testcase.setProblem(this);
        this.problemTestcases = testcases;
    }
}
