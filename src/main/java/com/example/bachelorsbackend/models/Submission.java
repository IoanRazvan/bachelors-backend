package com.example.bachelorsbackend.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Submission {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Problem problem;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    private String sourceCode;

    @ManyToOne
    private ProgrammingLanguage programmingLanguage;
}
