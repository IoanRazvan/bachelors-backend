package com.example.bachelorsbackend.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(ProblemCategoryKey.class)
@Data
public class ProblemCategory {
    @Id
    @ManyToOne
    private Problem problem;

    @Id
    @ManyToOne
    private Category category;
}
