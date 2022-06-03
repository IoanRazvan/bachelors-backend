package com.example.bachelorsbackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListRowDTO {
    private int id;
    private String listTitle;
    private boolean containsProblem;
}
