package com.quiz.api.repositories;
import com.quiz.api.models.Student;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
@Repository
public interface UpdateRepository extends CrudRepository<Student, Integer> {
    @Modifying
    @Query("UPDATE Student s SET s.lastname = :#{#entity.lastname}, s.firstname = :#{#entity.firstname}, s.address = :#{#entity.address}, s.birthdate = :#{#entity.birthdate},s.registeredAt = :#{#entity.registeredAt} WHERE s.id = :id")
    void updateById(@Param("id") Integer id, @Param("entity") Student entity);
}
