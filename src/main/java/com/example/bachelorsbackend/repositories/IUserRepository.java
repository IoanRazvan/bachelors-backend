package com.example.bachelorsbackend.repositories;

import com.example.bachelorsbackend.models.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
