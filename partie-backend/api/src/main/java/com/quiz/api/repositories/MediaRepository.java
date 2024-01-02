package com.quiz.api.repositories;

import com.quiz.api.models.Level;
import com.quiz.api.models.Media;
import com.quiz.api.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository  extends JpaRepository<Media, Integer> {
}
