package com.example.bachelorsbackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListRowDTO extends UserListResponseDTO {
    private boolean containsProblem;
}
