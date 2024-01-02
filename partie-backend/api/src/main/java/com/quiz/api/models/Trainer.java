package com.quiz.api.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table
public class Trainer extends Person {
    @Id
    @GeneratedValue
    private Integer id;
    private String speciality;
    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
    private List<Quiz> AssignQuiz;
}
