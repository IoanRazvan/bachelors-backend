package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.models.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
}
