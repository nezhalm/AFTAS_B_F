package com.quiz.api.repositories;
import com.quiz.api.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    List<Subject> findAllByParentId(Integer parentId);

}
