package com.quiz.api.DTOs.QuestionDTO;

import com.quiz.api.DTOs.LevelDTO.LevelDTO;
import com.quiz.api.DTOs.MediaDTO.MediaDTO;
import com.quiz.api.DTOs.SubjectDTO.SubjectDTO;
import com.quiz.api.DTOs.ValidationDTO.ValidationDTO;

import com.quiz.api.enums.ResponseType;
import lombok.Data;

import java.util.List;

@Data
    public class QuestionResponseDTO {
    private Integer id;
    private Integer numberOfResponses;
        private Integer numberOfCorrectResponses;
        private String content;
        private ResponseType type;
        private Integer points;
        private LevelDTO level;
        private SubjectDTO subject;
        private List<ValidationDTO> validations;
    private List<MediaDTO> medias;
    }






















