package com.example.bachelorsbackend.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ProgrammingLanguage {
    @Id
    private int id;

    private String languageName;
}
