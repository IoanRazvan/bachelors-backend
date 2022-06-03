package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.dtos.UserListProblemDTO;
import com.example.bachelorsbackend.services.IUserListProblemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-list-problem")
public class UserListProblemController {
    IUserListProblemService service;

    public UserListProblemController(IUserListProblemService service) {
        this.service = service;
    }

    @PostMapping
    void saveUserListProblem(@RequestBody UserListProblemDTO dto) {
        service.save(dto);
    }

    @DeleteMapping
    void removeUserListProblem(@RequestBody UserListProblemDTO dto) {
        service.delete(dto);
    }
}
