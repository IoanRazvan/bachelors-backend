package com.example.bachelorsbackend.repositories;

import com.example.bachelorsbackend.models.Submission;
import com.example.bachelorsbackend.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ISubmissionRepository extends CrudRepository<Submission, Integer> {
    List<Submission> findByUserAndProblemId(Sort sort, User user, int problemId);

    @Query("select ps.runtime, count(ps.runtime) " +
            "from PassingSubmission ps " +
            "where ps.submission.problem.id = ?1 and ps.submission.programmingLanguage.id = ?2 " +
            "group by ps.runtime " +
            "order by 1 asc")
    List<Object[]> getDistribution(int problemId, String languageId);

    @Query(value = "select to_char(s.timestamp, 'MM/DD/YYYY'), count(s.id) " +
            "from Submission  s " +
            "where s.user_subject = ?1 and to_char(current_date, 'YYYY') = to_char(s.timestamp, 'YYYY')" +
            "group by to_char(s.timestamp, 'MM/DD/YYYY')", nativeQuery = true)
    List<Object[]> getSubmissionsCount(String userId);
}
