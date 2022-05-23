package com.example.bachelorsbackend.repositories;

import com.example.bachelorsbackend.models.ProgrammingLanguage;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface IProgrammingLanguageRepository extends Repository<ProgrammingLanguage, Integer> {
    List<ProgrammingLanguage> findAll();
}
