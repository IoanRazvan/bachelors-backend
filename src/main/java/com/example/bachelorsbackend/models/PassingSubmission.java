package com.example.bachelorsbackend.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Data
public class PassingSubmission implements Serializable {
    @Id
    @ManyToOne
    private Submission submission;

    private float runtime;
}
