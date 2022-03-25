package com.example.bachelorsbackend.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Data
public class FailedSubmission implements Serializable {
    @Id
    @ManyToOne
    private Submission submission;

    private String failureReason;

    private String failedOutput;
}
