package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.dtos.userlist.UserListRequestDTO;
import com.example.bachelorsbackend.dtos.userlist.UserListResponseDTO;
import com.example.bachelorsbackend.dtos.userlist.UserListRowDTO;

import java.util.List;

public interface IUserListService {
    List<UserListRowDTO> getLists(int problemId);

    UserListResponseDTO save(UserListRequestDTO list);
}
