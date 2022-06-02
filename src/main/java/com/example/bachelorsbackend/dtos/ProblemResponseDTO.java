package com.example.bachelorsbackend.dtos;

import com.example.bachelorsbackend.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProblemResponseDTO {
    private int id;
    private String title;
    private String description;
    private List<ProblemStarterDTO> starters;
    private List<Category> categories;
}
