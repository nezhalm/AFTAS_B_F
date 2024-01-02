package com.quiz.api.DTOs.TrainerDTO;
import com.quiz.api.DTOs.QuizDTO.QuizDTO;
import com.quiz.api.models.Person;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TrainerResponseDTO extends Person {
    private Integer id;
    private String speciality;
    private List<QuizDTO> quizzes;
}

