package com.quiz.api.repositories;

import com.quiz.api.models.Level;
import com.quiz.api.models.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Integer> {
}
