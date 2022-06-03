package com.example.bachelorsbackend.services;

import java.util.List;

public interface IUserListService {
    List<Object[]> getLists(int problemId);
}
