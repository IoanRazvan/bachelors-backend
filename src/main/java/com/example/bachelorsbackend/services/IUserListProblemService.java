package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.dtos.problem.ListProblemDTO;
import com.example.bachelorsbackend.dtos.userlist.UserListProblemDTO;
import org.springframework.data.domain.Slice;

public interface IUserListProblemService {
    void save(UserListProblemDTO dto);

    void delete(UserListProblemDTO dto);

    Slice<ListProblemDTO> getListProblems(int listId, int page, int size);
}
