package com.quiz.api.repositories;

import com.quiz.api.models.Question;
import com.quiz.api.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.level LEFT JOIN FETCH q.subject")
    List<Question> findAllWithLevelAndSubject();

    //  récupérer des questions par niveau
    @Query("SELECT Q FROM Question Q WHERE Q.subject.id = :subjectId")
    List<Question> findAllBySubjectId(Integer subjectId);

    @Query("SELECT Q FROM Question Q WHERE Q.level.id = :levelId")
    List<Question> findAllByLevelId(Integer levelId);
    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.level l LEFT JOIN FETCH q.subject WHERE l.id = :levelId")
    List<Question> findByLevelId(@Param("levelId") Integer levelId);

}
