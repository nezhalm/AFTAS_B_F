package com.quiz.api.DTOs.ValidationDTO;

import com.quiz.api.DTOs.QuestionDTO.QuestionDTO;
import com.quiz.api.DTOs.ResponseDTO.ResponseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class ValidationResponseDTO {
    private Integer id;
    private Integer points;
    private QuestionDTO question;
    private ResponseDTO response;

}
