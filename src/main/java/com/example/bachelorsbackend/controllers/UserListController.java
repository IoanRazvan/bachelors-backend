package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.converters.ObjectArrayToUserListRowDTOConverter;
import com.example.bachelorsbackend.dtos.UserListRequestDTO;
import com.example.bachelorsbackend.dtos.UserListResponseDTO;
import com.example.bachelorsbackend.dtos.UserListRowDTO;
import com.example.bachelorsbackend.mappers.UserListMapper;
import com.example.bachelorsbackend.models.UserList;
import com.example.bachelorsbackend.services.IUserListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/user-list")
public class UserListController {
    private final IUserListService service;
    private final ObjectArrayToUserListRowDTOConverter converter;
    private final UserListMapper mapper;

    public UserListController(IUserListService service, ObjectArrayToUserListRowDTOConverter converter, UserListMapper mapper) {
        this.service = service;
        this.converter = converter;
        this.mapper = mapper;
    }

    @GetMapping("{id}")
    public ResponseEntity<List<UserListRowDTO>> getLists(@PathVariable int id) {
        List<Object[]> lists = service.getLists(id);
        List<UserListRowDTO> mappedLists = lists.stream().map(converter::convert).collect(Collectors.toList());
        return ok(mappedLists);
    }

    @PostMapping
    public ResponseEntity<UserListResponseDTO> saveList(@RequestBody UserListRequestDTO list) {
        UserList userList = service.saveList(list);
        return ok(mapper.entityToResponseDTO(userList));
    }
}
