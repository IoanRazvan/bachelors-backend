package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.models.User;
import com.example.bachelorsbackend.repositories.IUserListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.bachelorsbackend.services.ServiceUtils.getLoggedInUser;

@Service
public class UserListService implements IUserListService {
    private final IUserListRepository repo;

    public UserListService(IUserListRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Object[]> getLists(int problemId) {
        User user = getLoggedInUser();
        return repo.findByUserAndProblemId(user, problemId);
    }
}
