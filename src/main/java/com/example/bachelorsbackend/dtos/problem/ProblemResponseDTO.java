package com.example.bachelorsbackend.dtos.problem;

import com.example.bachelorsbackend.models.Category;
import com.example.bachelorsbackend.models.ProblemDifficulty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProblemResponseDTO {
    private int id;
    private String title;
    private String description;
    private ProblemDifficulty difficulty;
    private List<ProblemStarterDTO> starters;
    private List<Category> categories;
}
