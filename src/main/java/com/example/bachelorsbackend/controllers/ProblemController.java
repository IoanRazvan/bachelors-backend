package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.converters.ObjectArrayToProblemRowDTOConverter;
import com.example.bachelorsbackend.dtos.Page;
import com.example.bachelorsbackend.dtos.PageFactory;
import com.example.bachelorsbackend.dtos.ProblemResponseDTO;
import com.example.bachelorsbackend.dtos.ProblemRowDTO;
import com.example.bachelorsbackend.mappers.ProblemMapper;
import com.example.bachelorsbackend.models.Category;
import com.example.bachelorsbackend.models.Problem;
import com.example.bachelorsbackend.models.ProblemDifficulty;
import com.example.bachelorsbackend.services.IProblemService;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/problem")
public class ProblemController {
    private final IProblemService service;
    private final ProblemMapper mapper;
    private final ObjectArrayToProblemRowDTOConverter converter;

    public ProblemController(IProblemService service, ProblemMapper mapper, ObjectArrayToProblemRowDTOConverter converter) {
        this.service = service;
        this.mapper = mapper;
        this.converter = converter;
    }

    @GetMapping
    public Page<ProblemRowDTO> getProblems(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String status, @RequestParam(required = false) ProblemDifficulty difficulty, @RequestParam(required = false) List<Integer> categories, @RequestParam(defaultValue = "")  String query) {
        List<Category> mappedCategories = null;
        if (categories != null)
            mappedCategories = categories.stream().map(category -> {
                Category c = new Category();
                c.setId(category);
                return c;
            }).collect(Collectors.toList());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("page", page);
        parameters.put("size", size);
        parameters.put("status", status);
        parameters.put("difficulty", difficulty);
        parameters.put("categories", categories);
        parameters.put("query", query);
        Slice<Object[]> problems = service.findProblems(page, size, status, difficulty, mappedCategories, query);
        return PageFactory.of(problems, converter::convert, parameters);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProblemResponseDTO> getProblem(@PathVariable int id) {
        Problem problem = service.findById(id);
        return ok(mapper.problemEntityToResponseDto(problem));
    }
}
