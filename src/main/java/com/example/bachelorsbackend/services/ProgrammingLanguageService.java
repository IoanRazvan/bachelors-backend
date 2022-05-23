package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.models.ProgrammingLanguage;
import com.example.bachelorsbackend.repositories.IProgrammingLanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammingLanguageService implements IProgrammingLanguageService {
    private final IProgrammingLanguageRepository repo;

    public ProgrammingLanguageService(IProgrammingLanguageRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<ProgrammingLanguage> getAll() {
        return repo.findAll();
    }
}
