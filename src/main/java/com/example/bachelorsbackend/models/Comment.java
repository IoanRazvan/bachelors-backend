package com.example.bachelorsbackend.models;

import javax.persistence.*;

@Entity
@IdClass(CommentKey.class)
@Table(name = "PROBLEM_COMMENT")
public class Comment {
    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private Problem problem;

    private String commentText;
}
