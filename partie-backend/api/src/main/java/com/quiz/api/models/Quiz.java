package com.quiz.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.util.List;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//public class Quiz {
//
//    @Id
//    @GeneratedValue
//    private Integer id;
//    private Integer score;
//    private Boolean showResponses;
//    private Boolean showResult;
//    private Integer numberOfChances;
//    private String remarks;
//    private LocalTime duration;
//    @ManyToOne
//    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
//    private Trainer trainer;
//    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<QuizzQuestion> quizQuestions;
//    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<AssignQuizz> AssignQuizz;
//
//}


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Quiz {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer score;
    private Boolean showResponses;
    private Boolean showResult;
    private Integer numberOfChances;
    private String remarks;
    private LocalTime duration;
    @ManyToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private Trainer trainer;
    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    private List<QuizzQuestion> quizQuestions;
    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    private List<AssignQuizz> quizzes;

}

