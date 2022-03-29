package com.example.bachelorsbackend.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class ProblemSolution {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Problem problem;

    @ManyToOne
    private ProgrammingLanguage programmingLanguage;

    private String sourceCode;
}
