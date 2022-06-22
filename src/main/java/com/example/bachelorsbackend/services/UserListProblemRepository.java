package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.dtos.userlist.UserListProblemDTO;
import com.example.bachelorsbackend.mappers.UserListProblemMapper;
import com.example.bachelorsbackend.models.User;
import com.example.bachelorsbackend.models.UserList;
import com.example.bachelorsbackend.models.UserListProblem;
import com.example.bachelorsbackend.repositories.IUserListProblemRepository;
import com.example.bachelorsbackend.repositories.IUserListRepository;
import com.example.bachelorsbackend.services.exceptions.AccessDeniedException;
import com.example.bachelorsbackend.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.bachelorsbackend.services.ServiceUtils.getLoggedInUser;

@Service
public class UserListProblemRepository implements IUserListProblemService {
    IUserListRepository userListRepo;
    IUserListProblemRepository userListProblemRepo;
    UserListProblemMapper mapper;

    public UserListProblemRepository(IUserListRepository userListRepo, IUserListProblemRepository userListProblemRepo, UserListProblemMapper mapper) {
        this.userListRepo = userListRepo;
        this.userListProblemRepo = userListProblemRepo;
        this.mapper = mapper;
    }

    @Override
    public void save(UserListProblemDTO dto) {
        checkListRights(dto.getListId());
        UserListProblem entity = mapper.dtoToEntity(dto);
        System.out.println(entity.getUserList());
        System.out.println(entity.getProblem());
        userListProblemRepo.save(entity);
    }

    @Override
    public void delete(UserListProblemDTO dto) {
        checkListRights(dto.getListId());
        UserListProblem entity = mapper.dtoToEntity(dto);
        userListProblemRepo.delete(entity);
    }

    private void checkListRights(int listId) {
        Optional<UserList> userListOptional = userListRepo.findById(listId);
        User user = getLoggedInUser();
        userListOptional.ifPresentOrElse(userList -> {
            if (!userList.getUser().equals(user))
                throw new AccessDeniedException();
        }, ResourceNotFoundException::new);
    }
}
