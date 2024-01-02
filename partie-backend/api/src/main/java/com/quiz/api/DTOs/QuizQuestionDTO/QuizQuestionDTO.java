package com.quiz.api.DTOs.QuizQuestionDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class QuizQuestionDTO {

    private Integer id;
    private LocalTime duration;
    private Integer quizId;
    private Integer questionId;

}

