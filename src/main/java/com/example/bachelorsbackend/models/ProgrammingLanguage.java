package com.example.bachelorsbackend.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class ProgrammingLanguage {
    @Id
    @GeneratedValue
    private int id;

    private String languageName;
}
