package com.example.bachelorsbackend.dtos.problem;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SolvedProblemsStatsDTO {
    private List<DifficultyCountDTO> total;
    private List<DifficultyCountDTO> solved;
}
