package com.example.bachelorsbackend.controllers.handlers;

import com.example.bachelorsbackend.models.ProgrammingLanguage;
import com.example.bachelorsbackend.services.IProgrammingLanguageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("programming-language")
public class ProgrammingLanguageController {
    private final IProgrammingLanguageService service;

    public ProgrammingLanguageController(IProgrammingLanguageService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProgrammingLanguage> getSupportedProgrammingLanguages() {
        return this.service.getAll();
    }
}
