package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.converters.ObjectArrayToUserListRowDTOConverter;
import com.example.bachelorsbackend.dtos.UserListRowDTO;
import com.example.bachelorsbackend.services.IUserListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/list")
public class UserListController {
    private final IUserListService service;
    private final ObjectArrayToUserListRowDTOConverter converter;

    public UserListController(IUserListService service, ObjectArrayToUserListRowDTOConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping("{id}")
    public ResponseEntity<List<UserListRowDTO>> getLists(@PathVariable int id) {
        List<Object[]> lists = service.getLists(id);
        List<UserListRowDTO> mappedLists = lists.stream().map(converter::convert).collect(Collectors.toList());
        return ok(mappedLists);
    }
}
