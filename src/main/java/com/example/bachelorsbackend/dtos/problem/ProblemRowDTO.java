package com.example.bachelorsbackend.dtos.problem;

import com.example.bachelorsbackend.models.ProblemDifficulty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemRowDTO {
    private int id;
    private String title;
    private ProblemDifficulty difficulty;
    private String status;
}
