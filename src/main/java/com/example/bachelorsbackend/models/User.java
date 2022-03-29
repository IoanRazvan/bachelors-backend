package com.example.bachelorsbackend.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "APP_USER")
public class User {
    @Id
    @GeneratedValue
    private int id;

    private String email;

    private String firstName;

    private String lastName;

    private String role;
}
