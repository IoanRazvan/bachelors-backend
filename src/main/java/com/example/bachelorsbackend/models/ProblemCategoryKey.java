package com.example.bachelorsbackend.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProblemCategoryKey implements Serializable {
    private int problem;
    private int category;
}
