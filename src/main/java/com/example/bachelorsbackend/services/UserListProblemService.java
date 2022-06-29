package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.dtos.problem.ListProblemDTO;
import com.example.bachelorsbackend.dtos.userlist.UserListProblemDTO;
import com.example.bachelorsbackend.mappers.ProblemMapper;
import com.example.bachelorsbackend.mappers.UserListProblemMapper;
import com.example.bachelorsbackend.models.User;
import com.example.bachelorsbackend.models.UserList;
import com.example.bachelorsbackend.models.UserListProblem;
import com.example.bachelorsbackend.repositories.IUserListProblemRepository;
import com.example.bachelorsbackend.repositories.IUserListRepository;
import com.example.bachelorsbackend.services.exceptions.AccessDeniedException;
import com.example.bachelorsbackend.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.bachelorsbackend.services.ServiceUtils.getLoggedInUser;

@Service
public class UserListProblemService implements IUserListProblemService {
    private final IUserListRepository userListRepo;
    private final IUserListProblemRepository userListProblemRepo;
    private final UserListProblemMapper mapper;
    private final ProblemMapper problemMapper;

    public UserListProblemService(IUserListRepository userListRepo, IUserListProblemRepository userListProblemRepo, UserListProblemMapper mapper, ProblemMapper problemMapper) {
        this.userListRepo = userListRepo;
        this.userListProblemRepo = userListProblemRepo;
        this.mapper = mapper;
        this.problemMapper = problemMapper;
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

    @Override
    public Slice<ListProblemDTO> getListProblems(int listId, int page, int size) {
        Slice<UserListProblem> userListProblemSlice = userListProblemRepo.findByUserListId(PageRequest.of(page, size), listId);
        return userListProblemSlice.map(userListProblem -> problemMapper.entityToUserListDTO(userListProblem.getProblem()));
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
