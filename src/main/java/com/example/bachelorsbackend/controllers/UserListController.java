package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.dtos.UserListRequestDTO;
import com.example.bachelorsbackend.dtos.UserListResponseDTO;
import com.example.bachelorsbackend.dtos.UserListRowDTO;
import com.example.bachelorsbackend.services.IUserListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/user-list")
public class UserListController {
    private final IUserListService service;

    public UserListController(IUserListService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<List<UserListRowDTO>> getLists(@PathVariable int id) {
        return ok(service.getLists(id));
    }

    @PostMapping
    public ResponseEntity<UserListResponseDTO> saveList(@RequestBody UserListRequestDTO list) {
        return ok(service.save(list));
    }
}
