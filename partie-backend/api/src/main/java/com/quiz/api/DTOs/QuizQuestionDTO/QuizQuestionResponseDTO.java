package com.quiz.api.DTOs.QuizQuestionDTO;

import com.quiz.api.DTOs.QuestionDTO.QuestionDTO;
import com.quiz.api.DTOs.QuizDTO.QuizDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;


    @Data
    @NoArgsConstructor
    public class QuizQuestionResponseDTO {

        private Integer id;
        private LocalTime duration;
        private QuizDTO quiz;
        private QuestionDTO question;


}
