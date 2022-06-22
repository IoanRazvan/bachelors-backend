package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.dtos.userlist.UserListRequestDTO;
import com.example.bachelorsbackend.dtos.userlist.UserListResponseDTO;
import com.example.bachelorsbackend.dtos.userlist.UserListRowDTO;
import com.example.bachelorsbackend.mappers.UserListMapper;
import com.example.bachelorsbackend.models.User;
import com.example.bachelorsbackend.models.UserList;
import com.example.bachelorsbackend.repositories.IUserListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.bachelorsbackend.services.ServiceUtils.getLoggedInUser;

@Service
public class UserListService implements IUserListService {
    private final IUserListRepository repo;
    private final UserListMapper mapper;

    public UserListService(IUserListRepository repo, UserListMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public List<UserListRowDTO> getLists(int problemId) {
        User user = getLoggedInUser();
        return repo.findByUserAndProblemId(user, problemId).stream().map(mapper::objectArrayToUserListRow).collect(Collectors.toList());
    }

    @Override
    public UserListResponseDTO save(UserListRequestDTO list) {
        UserList entity = new UserList();
        entity.setUser(getLoggedInUser());
        entity.setListTitle(list.getListTitle());
        return mapper.entityToResponseDTO(repo.save(entity));
    }
}
