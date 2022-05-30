package com.example.bachelorsbackend.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ProgrammingLanguage {
    @Id
    private String id;

    private String languageName;

    public ProgrammingLanguage(String id) {
        this.id = id;
    }

    public ProgrammingLanguage() {
    }
}
