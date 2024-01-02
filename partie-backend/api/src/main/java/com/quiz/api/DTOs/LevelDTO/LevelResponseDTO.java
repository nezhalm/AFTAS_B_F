package com.quiz.api.DTOs.LevelDTO;

import com.quiz.api.DTOs.QuestionDTO.QuestionDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
    @NoArgsConstructor
    public class LevelResponseDTO {
        private Integer id;
        private String description;
        private Integer maxPoints ;
        private Integer minPoints ;
        private List<QuestionDTO> questions;
    }


