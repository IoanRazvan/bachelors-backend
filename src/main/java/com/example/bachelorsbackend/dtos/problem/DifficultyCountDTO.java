package com.example.bachelorsbackend.dtos.problem;

import com.example.bachelorsbackend.models.ProblemDifficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class DifficultyCountDTO {
    private ProblemDifficulty difficulty;
    private BigDecimal count;

    public DifficultyCountDTO(ProblemDifficulty difficulty, BigDecimal count) {
        this.difficulty = difficulty;
        this.count = count;
    }
}
