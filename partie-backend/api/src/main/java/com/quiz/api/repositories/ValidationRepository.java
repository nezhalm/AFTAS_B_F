package com.quiz.api.repositories;

import com.quiz.api.models.Response;
import com.quiz.api.models.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidationRepository extends JpaRepository<Validation, Integer> {
    boolean existsByQuestion_IdAndResponse_Id(Integer questionId, Integer responseId);

}
