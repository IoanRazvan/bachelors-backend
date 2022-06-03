package com.example.bachelorsbackend.mappers;

import com.example.bachelorsbackend.dtos.UserListResponseDTO;
import com.example.bachelorsbackend.models.UserList;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserListMapper {
    public abstract UserListResponseDTO entityToResponseDTO(UserList entity);
}
