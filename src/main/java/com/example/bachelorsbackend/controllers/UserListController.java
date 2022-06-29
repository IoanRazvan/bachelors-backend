package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.dtos.userlist.UserListRequestDTO;
import com.example.bachelorsbackend.dtos.userlist.UserListResponseDTO;
import com.example.bachelorsbackend.dtos.userlist.UserListRowDTO;
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

    @GetMapping("problem/{id}")
    public ResponseEntity<List<UserListRowDTO>> getLists(@PathVariable int id) {
        return ok(service.getLists(id));
    }

    @PostMapping
    public ResponseEntity<UserListResponseDTO> saveList(@RequestBody UserListRequestDTO list) {
        return ok(service.save(list));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserListResponseDTO> updateList(@PathVariable int id, @RequestBody UserListRequestDTO list) {
        return ok(service.update(id, list));
    }

    @GetMapping
    public ResponseEntity<List<UserListResponseDTO>> getLists() {
        return ok(service.getAll());
    }

    @DeleteMapping("{id}")
    public void deleteList(@PathVariable int id) {
        service.delete(id);
    }

}
