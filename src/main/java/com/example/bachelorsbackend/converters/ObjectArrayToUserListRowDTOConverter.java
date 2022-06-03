package com.example.bachelorsbackend.converters;

import com.example.bachelorsbackend.dtos.UserListRowDTO;
import org.springframework.core.convert.converter.Converter;

public class ObjectArrayToUserListRowDTOConverter implements Converter<Object[], UserListRowDTO> {

    @Override
    public UserListRowDTO convert(Object[] source) {
        UserListRowDTO target = new UserListRowDTO();
        target.setId((Integer)source[0]);
        target.setListTitle((String)source[1]);
        target.setContainsProblem((Boolean)source[2]);
        return target;
    }
}
