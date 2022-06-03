package com.example.bachelorsbackend.repositories;

import com.example.bachelorsbackend.models.UserListProblem;
import com.example.bachelorsbackend.models.UserListProblemKey;
import org.springframework.data.repository.CrudRepository;

public interface IUserListProblemRepository extends CrudRepository<UserListProblem, UserListProblemKey> {
}
