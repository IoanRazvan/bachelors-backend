package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.models.ProgrammingLanguage;

import java.util.List;

public interface IProgrammingLanguageService {
    List<ProgrammingLanguage> getAll();
}
