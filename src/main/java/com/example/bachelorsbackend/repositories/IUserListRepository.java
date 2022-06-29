package com.example.bachelorsbackend.repositories;

import com.example.bachelorsbackend.models.User;
import com.example.bachelorsbackend.models.UserList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserListRepository extends CrudRepository<UserList, Integer> {
    @Query("select ul.id, ul.listTitle, (case when ulp.problem is null then false else true end) as contains " +
            "from UserList ul left join UserListProblem ulp on (ul = ulp.userList and ulp.problem.id = ?2) " +
            "where ul.user = ?1")
    List<Object[]> findByUserAndProblemId(User owner, int problemId);

    List<UserList> findByUser(User user);
}
