package com.example.bachelorsbackend.repositories;

import com.example.bachelorsbackend.models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICategoryRepository extends CrudRepository<Category, Integer> {
    List<Category> findAll();
}
