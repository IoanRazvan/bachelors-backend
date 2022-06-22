package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.dtos.userlist.UserListProblemDTO;

public interface IUserListProblemService {
    void save(UserListProblemDTO dto);
    void delete(UserListProblemDTO dto);
}
