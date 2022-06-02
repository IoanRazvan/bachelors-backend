package com.example.bachelorsbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FailedSubmission implements Serializable {
    @Id
    @OneToOne
    private Submission submission;

    @Lob
    private String error;

    @Lob
    private String input;

    @Lob
    private String output;

    @Lob
    private String expected;
}
