package com.example.bachelorsbackend.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserListProblemKey implements Serializable {
    private int problem;
    private int userList;
}
