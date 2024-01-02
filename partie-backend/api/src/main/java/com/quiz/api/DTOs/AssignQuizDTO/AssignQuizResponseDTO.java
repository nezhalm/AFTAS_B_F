package com.quiz.api.DTOs.AssignQuizDTO;

import com.quiz.api.DTOs.QuizDTO.QuizDTO;
import com.quiz.api.DTOs.StudentDTO.StudentDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AssignQuizResponseDTO {

    private Integer id;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private Integer score;
    private String result;
    private QuizDTO quiz;
    private StudentDTO student;

}
