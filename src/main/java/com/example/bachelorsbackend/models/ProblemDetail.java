package com.example.bachelorsbackend.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class ProblemDetail {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Problem problem;

    private String detailText;

    private String detailType;
}
