package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.models.Problem;
import com.example.bachelorsbackend.repositories.IProblemRepository;
import org.springframework.stereotype.Service;

@Service
public class ProblemService implements IProblemService {
    private final IProblemRepository repo;

    public ProblemService(IProblemRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(Problem p) {
        repo.save(p);
    }
}
