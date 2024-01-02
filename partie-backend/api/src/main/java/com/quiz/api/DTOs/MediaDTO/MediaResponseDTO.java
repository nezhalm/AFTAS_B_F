package com.quiz.api.DTOs.MediaDTO;

import com.quiz.api.DTOs.QuestionDTO.QuestionDTO;
import com.quiz.api.enums.MediaType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MediaResponseDTO {
    private Integer id;
    private MediaType type;
    private String url;
    private QuestionDTO question;

}
