package com.example.bachelorsbackend.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentKey implements Serializable {
    private String user;

    private int problem;
}
