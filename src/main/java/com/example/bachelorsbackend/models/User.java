package com.example.bachelorsbackend.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "APP_USER")
public class User {
    @Id
    @GeneratedValue
    private int id;

    private String subject;

    private String username;
}
