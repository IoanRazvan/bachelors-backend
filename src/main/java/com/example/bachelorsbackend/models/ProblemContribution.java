package com.example.bachelorsbackend.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="PROBLEM_CONTRIBUTION")
@EntityListeners(AuditingEntityListener.class)
@Data
public class ProblemContribution {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User contributor;

    private String title;

    @Lob
    private String description;

    private String solution;

    private String testcase;

    @Enumerated(EnumType.STRING)
    private ProblemContributionStatus status;

    private String statusDetails;

    @CreatedDate
    private LocalDateTime createdTime;
}
