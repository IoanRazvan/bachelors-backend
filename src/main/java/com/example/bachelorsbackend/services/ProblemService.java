package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.dtos.problem.ProblemResponseDTO;
import com.example.bachelorsbackend.dtos.problem.ProblemRowDTO;
import com.example.bachelorsbackend.dtos.problem.SolvedProblemsStatsDTO;
import com.example.bachelorsbackend.mappers.ProblemMapper;
import com.example.bachelorsbackend.models.Category;
import com.example.bachelorsbackend.models.Problem;
import com.example.bachelorsbackend.models.ProblemDifficulty;
import com.example.bachelorsbackend.models.User;
import com.example.bachelorsbackend.repositories.IProblemRepository;
import com.example.bachelorsbackend.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.bachelorsbackend.services.ServiceUtils.getLoggedInUser;

@Service
public class ProblemService implements IProblemService {
    private final ProblemMapper mapper;

    @FunctionalInterface
    private interface ProblemsFilteringQuery {
        Slice<Object[]> getProblems(Pageable page, User user, String status, List<Category> categories, ProblemDifficulty difficulty, String query);
    }

    private final IProblemRepository repo;
    private final Map<Integer, ProblemsFilteringQuery> methods;

    public ProblemService(IProblemRepository repo, ProblemMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
        this.methods = Map.of(
                0, (page, user, status, categories, difficulty, query) -> repo.getProblems(page, user, query),
                1, (page, user, status, categories, difficulty, query) -> repo.getProblemsByStatus(page, user, status, query),
                2, (page, user, status, categories, difficulty, query) -> repo.getProblemsByDifficulty(page, user, difficulty, query),
                3, (page, user, status, categories, difficulty, query) -> repo.getProblemsByDifficultyAndStatus(page, user, difficulty, status, query),
                4, (page, user, status, categories, difficulty, query) -> repo.getProblemsByCategories(page, user, categories, query),
                5, (page, user, status, categories, difficulty, query) -> repo.getProblemsByCategoriesAndStatus(page, user, categories, status, query),
                6, (page, user, status, categories, difficulty, query) -> repo.getProblemsByDifficultyAndCategories(page, user, difficulty, categories, query),
                7, (page, user, status, categories, difficulty, query) -> repo.getProblemsByCategoriesAndStatusAndDifficulty(page, user, categories, status, difficulty, query)
        );
    }

    @Override
    public void save(Problem p) {
        repo.save(p);
    }

    @Override
    public ProblemResponseDTO findById(Integer id) {
        Optional<Problem> problem = repo.findById(id);
        return mapper.problemEntityToResponseDto(problem.orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public Slice<ProblemRowDTO> findProblems(int page, int size, String status, ProblemDifficulty difficulty, List<Category> categories, String query) {
        Pageable pageable = PageRequest.of(page, size);
        User user = getLoggedInUser();

        Slice<Object[]> result = methods.get(getParamSum(status, difficulty, categories)).getProblems(pageable, user, status, categories, difficulty, query);
        return result.map(mapper::objectArrayToProblemRow);
    }

    @Override
    public SolvedProblemsStatsDTO getSolvedProblemsStats() {
        User u = getLoggedInUser();
        List<Object[]> total = repo.countProblemsGroupByDifficulty();
        List<Object[]> solved = repo.countProblemsSolvedGroupByDifficulty(u.getSubject());
        return mapper.statsListsToSolvedProblemStats(total, solved);
    }

    private int getParamSum(String status, ProblemDifficulty difficulty, List<Category> categories) {
        int sum = 0;
        if (status != null)
            sum += 1;
        if (difficulty != null)
            sum += 2;
        if (categories != null)
            sum += 4;
        return sum;
    }
}
