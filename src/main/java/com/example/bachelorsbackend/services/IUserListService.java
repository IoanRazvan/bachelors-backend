package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.dtos.UserListRequestDTO;
import com.example.bachelorsbackend.dtos.UserListResponseDTO;
import com.example.bachelorsbackend.dtos.UserListRowDTO;

import java.util.List;

public interface IUserListService {
    List<UserListRowDTO> getLists(int problemId);

    UserListResponseDTO save(UserListRequestDTO list);
}
