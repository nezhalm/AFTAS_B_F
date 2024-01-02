package com.quiz.api.DTOs.QuizDTO;

import com.quiz.api.DTOs.AssignQuizDTO.AssignQuizDTO;
import com.quiz.api.DTOs.QuizQuestionDTO.QuizQuestionDTO;
import com.quiz.api.DTOs.TrainerDTO.TrainerResponseDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
public class QuizResponseDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer score;
    private Boolean showResponses;
    private Boolean showResult;
    private Integer numberOfChances;
    private String remarks;
    private LocalTime duration;
    private TrainerResponseDTO trainer;
    private List<QuizQuestionDTO> quizQuestions;
    private List<AssignQuizDTO> assignQuizzes;

}
