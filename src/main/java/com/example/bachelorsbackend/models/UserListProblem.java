package com.example.bachelorsbackend.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@Data
@IdClass(UserListProblemKey.class)
public class UserListProblem {
    @Id
    @ManyToOne
    private UserList userList;

    @Id
    @ManyToOne
    private Problem problem;
}
