package com.example.bachelorsbackend.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "APP_USER")
public class User {
    @Id
    private int id;

    private String email;

    private String firstName;

    private String lastName;

    private String role;
}
