package com.quiz.api.DTOs.AssignQuizDTO;

import com.quiz.api.DTOs.QuizDTO.QuizDTO;
import com.quiz.api.DTOs.QuizDTO.QuizResponseDTO;
import com.quiz.api.repositories.QuizRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AssignQuizDTO {

    private Integer id;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private Integer score;
    private String result;
    private Integer quiz;
    private Integer studentId;

}
