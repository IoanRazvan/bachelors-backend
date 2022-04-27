package com.example.bachelorsbackend.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="PROBLEM_CONTRIBUTION")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class ProblemContribution {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User contributor;

    private String title;

    @Lob
    private String description;

    @Lob
    private String solution;

    private String testcase;

    @Enumerated(EnumType.STRING)
    private ProblemContributionStatus status;

    private String statusDetails;

    @CreatedDate
    private LocalDateTime createdTime;

    @ManyToOne
    private User assignedTo;

    @Version
    private long version;

    public ProblemContribution(ProblemContribution p) {
        id = p.id;
        contributor = p.contributor;
        title = p.title;
        description = p.description;
        solution = p.solution;
        testcase = p.testcase;
        status = p.status;
        statusDetails = p.statusDetails;
        createdTime = p.createdTime;
        version = p.version;
        assignedTo = p.assignedTo;
    }
}
