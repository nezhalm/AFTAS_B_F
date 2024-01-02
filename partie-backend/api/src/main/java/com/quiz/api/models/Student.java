package com.quiz.api.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table

public class Student extends Person{
    @Id
    @GeneratedValue
    private Integer id;
    private LocalDate registeredAt;


    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY )
    private List<AssignQuizz> AssignQuiz;
}
