package com.example.bachelorsbackend.dtos.problem;

import com.example.bachelorsbackend.models.Category;
import com.example.bachelorsbackend.models.ProblemDifficulty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProblemRequestDTO {
    private String title;
    private String description;
    private List<ProblemSolutionDTO> solutions;
    private List<ProblemTestcaseDTO> testcases;
    private ProblemDifficulty difficulty;
    private List<Category> categories;
    private int contributionId;
}
