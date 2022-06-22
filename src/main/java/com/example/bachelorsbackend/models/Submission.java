package com.example.bachelorsbackend.models;

import com.example.bachelorsbackend.dtos.coderunner.CodeRunnerResult;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Submission {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Problem problem;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date timestamp;

    @Lob
    private String sourceCode;

    private int statusCode;

    @ManyToOne
    private ProgrammingLanguage programmingLanguage;

    @OneToOne(mappedBy = "submission", cascade = CascadeType.ALL)
    private PassingSubmission passingSubmission;

    @OneToOne(mappedBy = "submission", cascade = CascadeType.ALL)
    private FailedSubmission failedSubmission;

    public Submission(User user, int problemId, String sourceCode, CodeRunnerResult result) {
        this.user = user;
        this.problem = new Problem();
        this.problem.setId(problemId);
        this.sourceCode = sourceCode;
        this.statusCode = result.getStatus();
        this.programmingLanguage = new ProgrammingLanguage(result.getLangId());
        if (result.getStatus() == 0)
            this.passingSubmission = new PassingSubmission(this, result.getRuntime());
        else
            this.failedSubmission = new FailedSubmission(this, result.getError(), result.getWrongAnswer().getInput(), result.getWrongAnswer().getActual(), result.getWrongAnswer().getExpected());
    }

    @PrePersist
    void timestamp() {
        this.timestamp = new Date();
    }
}
