package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.models.Category;
import com.example.bachelorsbackend.models.Problem;
import com.example.bachelorsbackend.models.ProblemDifficulty;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface IProblemService {
    void save(Problem p);

    Problem findById(Integer id);

    Slice<Object[]> findProblems(int page, int size, String status, ProblemDifficulty difficulty, List<Category> categories, String query);
}
