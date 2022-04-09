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

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final User otherUser = (User) o;
        return otherUser.getId() == getId();
    }
}
