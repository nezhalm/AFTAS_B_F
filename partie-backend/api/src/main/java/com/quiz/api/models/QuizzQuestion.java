package com.quiz.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@NoArgsConstructor
@Data
@Entity
@Table
public class QuizzQuestion {

    @Id
    @GeneratedValue
    private Integer id;
    private Duration duration;
    @ManyToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

}
