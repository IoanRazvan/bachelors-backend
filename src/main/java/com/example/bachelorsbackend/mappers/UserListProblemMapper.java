package com.example.bachelorsbackend.mappers;

import com.example.bachelorsbackend.dtos.UserListProblemDTO;
import com.example.bachelorsbackend.models.UserListProblem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserListProblemMapper {
    @Mapping(target="problem", expression = "java(new com.example.bachelorsbackend.models.Problem (dto.getProblemId()))")
    @Mapping(target="userList", expression = "java(new com.example.bachelorsbackend.models.UserList( dto.getListId() ))")
    public abstract UserListProblem dtoToEntity(UserListProblemDTO dto);
}
