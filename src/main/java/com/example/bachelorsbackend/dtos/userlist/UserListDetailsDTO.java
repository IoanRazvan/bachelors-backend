package com.example.bachelorsbackend.dtos.userlist;

import com.example.bachelorsbackend.dtos.problem.ListProblemDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserListDetailsDTO extends UserListResponseDTO {
    private List<ListProblemDTO> problems;
}
