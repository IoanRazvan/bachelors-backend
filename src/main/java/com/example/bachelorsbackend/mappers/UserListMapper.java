package com.example.bachelorsbackend.mappers;

import com.example.bachelorsbackend.dtos.userlist.UserListResponseDTO;
import com.example.bachelorsbackend.dtos.userlist.UserListRowDTO;
import com.example.bachelorsbackend.models.UserList;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserListMapper {
    public abstract UserListResponseDTO entityToResponseDTO(UserList entity);

    public UserListRowDTO objectArrayToUserListRow(Object[] source) {
        UserListRowDTO target = new UserListRowDTO();
        target.setId((Integer) source[0]);
        target.setListTitle((String) source[1]);
        target.setContainsProblem((Boolean) source[2]);
        return target;
    }
}
