package com.example.bachelorsbackend.repositories;

import com.example.bachelorsbackend.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<User, Integer> {
    Optional<User> findBySubject(String subject);
}
