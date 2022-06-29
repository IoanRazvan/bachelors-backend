package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.dtos.Page;
import com.example.bachelorsbackend.dtos.PageFactory;
import com.example.bachelorsbackend.dtos.problem.ListProblemDTO;
import com.example.bachelorsbackend.dtos.userlist.UserListProblemDTO;
import com.example.bachelorsbackend.services.IUserListProblemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/user-list-problem")
public class UserListProblemController {
    private final IUserListProblemService service;

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

    @GetMapping("{listId}")
    ResponseEntity<Page<ListProblemDTO>> getListProblems(@PathVariable int listId, @RequestParam int page, @RequestParam int size) {
        return ok(PageFactory.of(service.getListProblems(listId, page, size)));
    }
}
