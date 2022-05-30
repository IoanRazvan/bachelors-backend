package com.example.bachelorsbackend.models;

import lombok.Data;

import javax.persistence.*;

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

    @Lob
    private String sourceCode;
}
