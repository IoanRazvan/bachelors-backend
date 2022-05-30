package com.example.bachelorsbackend.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ProblemTestcase {
    @Id
    @GeneratedValue
    private int id;

    @Lob
    private String input;

    @Lob
    private String output;

    @ManyToOne
    private Problem problem;
}
