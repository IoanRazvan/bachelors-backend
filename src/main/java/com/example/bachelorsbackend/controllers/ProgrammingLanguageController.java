package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.models.ProgrammingLanguage;
import com.example.bachelorsbackend.services.IProgrammingLanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/programming-language")
public class ProgrammingLanguageController {
    private final IProgrammingLanguageService service;

    public ProgrammingLanguageController(IProgrammingLanguageService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProgrammingLanguage>> getProgrammingLanguages() {
        return ok(service.getAll());
    }
}
