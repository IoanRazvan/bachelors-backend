package com.example.bachelorsbackend.repositories;

import com.example.bachelorsbackend.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
