package com.example.bachelorsbackend.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class UserList {
    @Id
    @GeneratedValue
    private int id;

    private String listTitle;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "problem")
    private List<UserListProblem> userListProblems;
}
