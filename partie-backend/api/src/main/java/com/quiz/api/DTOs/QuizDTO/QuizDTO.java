package com.quiz.api.DTOs.QuizDTO;

import com.quiz.api.DTOs.TrainerDTO.TrainerDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@Data
@NoArgsConstructor
public class QuizDTO {
        private Integer id;
        private Integer score;
        private Boolean showResponses;
        private Boolean showResult;
        private Integer numberOfChances;
        private String remarks;
        private LocalTime duration;
        private TrainerDTO trainer;

    }

