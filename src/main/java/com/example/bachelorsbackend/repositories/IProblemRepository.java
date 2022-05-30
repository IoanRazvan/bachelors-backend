package com.example.bachelorsbackend.repositories;

import com.example.bachelorsbackend.models.Problem;
import org.springframework.data.repository.CrudRepository;

public interface IProblemRepository extends CrudRepository<Problem, Integer> {
}
