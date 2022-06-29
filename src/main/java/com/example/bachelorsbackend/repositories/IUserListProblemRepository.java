package com.example.bachelorsbackend.repositories;

import com.example.bachelorsbackend.models.UserListProblem;
import com.example.bachelorsbackend.models.UserListProblemKey;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;

public interface IUserListProblemRepository extends CrudRepository<UserListProblem, UserListProblemKey> {
    Slice<UserListProblem> findByUserListId(Pageable pageable, int userListId);
}
