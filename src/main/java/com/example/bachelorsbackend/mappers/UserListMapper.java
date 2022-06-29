package com.example.bachelorsbackend.mappers;

import com.example.bachelorsbackend.dtos.userlist.UserListDetailsDTO;
import com.example.bachelorsbackend.dtos.userlist.UserListResponseDTO;
import com.example.bachelorsbackend.dtos.userlist.UserListRowDTO;
import com.example.bachelorsbackend.models.UserList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UserListMapper {
    @Autowired
    protected ProblemMapper problemMapper;

    public abstract UserListResponseDTO entityToResponseDTO(UserList entity);

    public UserListRowDTO objectArrayToUserListRow(Object[] source) {
        UserListRowDTO target = new UserListRowDTO();
        target.setId((Integer) source[0]);
        target.setListTitle((String) source[1]);
        target.setContainsProblem((Boolean) source[2]);
        return target;
    }

    @Mapping(target = "problems", expression = "java(entity.getUserListProblems().stream().map(ulp -> ulp.getProblem()).map(problemMapper::entityToUserListDTO).collect(java.util.stream.Collectors.toList()))")
    public abstract UserListDetailsDTO entityToDetailsDTO(UserList entity);
}
