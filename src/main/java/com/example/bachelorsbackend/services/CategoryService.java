package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.models.Category;
import com.example.bachelorsbackend.repositories.ICategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    private ICategoryRepository repo;

    public CategoryService(ICategoryRepository repo) {
        this.repo = repo;
    }
    @Override
    public List<Category> findAll() {
        return this.repo.findAll();
    }
}
