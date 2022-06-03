package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.dtos.UserListRequestDTO;
import com.example.bachelorsbackend.models.UserList;

import java.util.List;

public interface IUserListService {
    List<Object[]> getLists(int problemId);
    UserList saveList(UserListRequestDTO list);
}
