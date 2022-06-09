package com.example.bachelorsbackend.repositories;

import com.example.bachelorsbackend.models.Category;
import com.example.bachelorsbackend.models.Problem;
import com.example.bachelorsbackend.models.ProblemDifficulty;
import com.example.bachelorsbackend.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProblemRepository extends CrudRepository<Problem, Integer> {
    @Query("select p.id, p.title, p.difficulty, case " +
            "when not exists (select 1 from Submission s where s.problem = p and s.user = ?1) then 'Todo' " +
            "when exists (select 1 from Submission s where s.problem = p and s.statusCode = 0 and s.user = ?1) then 'Solved' " +
            "else 'Attempted' " +
            "end as status " +
            "from Problem p " +
            "where p.title like concat('%', ?2, '%')")
    Slice<Object[]> getProblems(Pageable page, User u, String query);

    @Query("select p.id, p.title, p.difficulty, case " +
            "when not exists (select 1 from Submission s where s.problem = p and s.user = ?1) then 'Todo' " +
            "when exists (select 1 from Submission s where s.problem = p and s.statusCode = 0 and s.user = ?1) then 'Solved' " +
            "else 'Attempted' " +
            "end as status " +
            "from Problem p where p.difficulty = ?2 and p.title like concat('%', ?3, '%')")
    Slice<Object[]> getProblemsByDifficulty(Pageable page, User u, ProblemDifficulty difficulty, String query);

    @Query("select p.id, p.title, p.difficulty, case " +
            "when not exists (select 1 from Submission s where s.problem = p and s.user = ?1) then 'Todo' " +
            "when exists (select 1 from Submission s where s.problem = p and s.statusCode = 0 and s.user = ?1) then 'Solved' " +
            "else 'Attempted' " +
            "end as status " +
            "from Problem p " +
            "where p.title like concat('%', ?3, '%') and not exists (select 1 from Category c where c in ?2 and not exists (select 1 from p.problemCategories pc where pc = c))")
    Slice<Object[]> getProblemsByCategories(Pageable page, User user, List<Category> categories, String query);

    @Query("select p.id, p.title, p.difficulty, concat('', ?2) as status " +
            "from Problem p "  +
            "where p.title like concat('%', ?3, '%') and concat('', ?2) in (select case " +
            "when not exists (select 1 from Submission s where s.problem = p and s.user = ?1) then 'Todo' " +
            "when exists (select 1 from Submission s where s.problem = p and s.statusCode = 0 and s.user = ?1) then 'Solved' " +
            "else 'Attempted' " +
            "end from ProgrammingLanguage)")
    Slice<Object[]> getProblemsByStatus(Pageable page, User user, String status, String query);

    @Query("select p.id, p.title, p.difficulty, case " +
            "when not exists (select 1 from Submission s where s.problem = p and s.user = ?1) then 'Todo' " +
            "when exists (select 1 from Submission s where s.problem = p and s.statusCode = 0 and s.user = ?1) then 'Solved' " +
            "else 'Attempted' " +
            "end as status " +
            "from Problem p " +
            "where p.difficulty = ?2 and p.title like concat('%', ?4, '%') and not exists (select 1 from Category c where c in ?3 and not exists (select 1 from p.problemCategories pc where pc = c))")
    Slice<Object[]> getProblemsByDifficultyAndCategories(Pageable page, User user, ProblemDifficulty difficulty, List<Category> categories, String query);

    @Query("select p.id, p.title, p.difficulty, concat('', ?3) as status " +
            "from Problem p "  +
            "where p.difficulty = ?2 and p.title like concat('%', ?4, '%') and concat('', ?3) in (select case " +
            "when not exists (select 1 from Submission s where s.problem = p and s.user = ?1) then 'Todo' " +
            "when exists (select 1 from Submission s where s.problem = p and s.statusCode = 0 and s.user = ?1) then 'Solved' " +
            "else 'Attempted' " +
            "end from ProgrammingLanguage)")
    Slice<Object[]> getProblemsByDifficultyAndStatus(Pageable page, User user, ProblemDifficulty difficulty, String status, String query);

    @Query("select p.id, p.title, p.difficulty, concat('', ?3) as status " +
            "from Problem p " +
            "where p.title like concat('%', ?4, '%') and concat('', ?3) in (select case " +
            "when not exists (select 1 from Submission s where s.problem = p and s.user = ?1) then 'Todo' " +
            "when exists (select 1 from Submission s where s.problem = p and s.statusCode = 0 and s.user = ?1) then 'Solved' " +
            "else 'Attempted' " +
            "end from ProgrammingLanguage) and " +
            "not exists (select 1 from Category c where c in ?2 and not exists (select 1 from p.problemCategories pc where pc = c))")
    Slice<Object[]> getProblemsByCategoriesAndStatus(Pageable page, User user, List<Category> categories, String status, String query);

    @Query("select p.id, p.title, p.difficulty, concat('', ?3) as status " +
            "from Problem p " +
            "where p.difficulty = ?4 and p.title like concat('%', ?5, '%') and concat('', ?3) in (select case " +
            "when not exists (select 1 from Submission s where s.problem = p and s.user = ?1) then 'Todo' " +
            "when exists (select 1 from Submission s where s.problem = p and s.statusCode = 0 and s.user = ?1) then 'Solved' " +
            "else 'Attempted' " +
            "end from ProgrammingLanguage) and " +
            "not exists (select 1 from Category c where c in ?2 and not exists (select 1 from p.problemCategories pc where pc = c))")
    Slice<Object[]> getProblemsByCategoriesAndStatusAndDifficulty(Pageable page, User user, List<Category> categories, String status, ProblemDifficulty difficulty, String query);
}
