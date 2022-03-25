package com.example.bachelorsbackend.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class UserList {
    @Id
    private int id;

    private String listTitle;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "problem")
    private List<UserListProblem> userListProblems;
}
