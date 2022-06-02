package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.models.Category;
import com.example.bachelorsbackend.services.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    private ICategoryService service;

    public CategoryController(ICategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ok(service.findAll());
    }
}
