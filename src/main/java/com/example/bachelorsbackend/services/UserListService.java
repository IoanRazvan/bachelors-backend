package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.dtos.UserListRequestDTO;
import com.example.bachelorsbackend.dtos.UserListResponseDTO;
import com.example.bachelorsbackend.dtos.UserListRowDTO;
import com.example.bachelorsbackend.mappers.UserListMapper;
import com.example.bachelorsbackend.models.User;
import com.example.bachelorsbackend.models.UserList;
import com.example.bachelorsbackend.repositories.IUserListRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.bachelorsbackend.services.ServiceUtils.getLoggedInUser;

@Service
public class UserListService implements IUserListService {
    private final IUserListRepository repo;
    private final Converter<Object[], UserListRowDTO> converter;
    private final UserListMapper mapper;

    public UserListService(IUserListRepository repo, Converter<Object[], UserListRowDTO> converter, UserListMapper mapper) {
        this.repo = repo;
        this.converter = converter;
        this.mapper = mapper;
    }

    @Override
    public List<UserListRowDTO> getLists(int problemId) {
        User user = getLoggedInUser();
        return repo.findByUserAndProblemId(user, problemId).stream().map(converter::convert).collect(Collectors.toList());
    }

    @Override
    public UserListResponseDTO save(UserListRequestDTO list) {
        UserList entity = new UserList();
        entity.setUser(getLoggedInUser());
        entity.setListTitle(list.getListTitle());
        return mapper.entityToResponseDTO(repo.save(entity));
    }
}
