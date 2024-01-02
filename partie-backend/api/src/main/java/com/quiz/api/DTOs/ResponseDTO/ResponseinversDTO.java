package com.quiz.api.DTOs.ResponseDTO;

import com.quiz.api.DTOs.ValidationDTO.ValidationDTO;
import com.quiz.api.models.Validation;

import java.util.List;

public class ResponseinversDTO {
    private Integer id;
    private String content;
    private List<ValidationDTO> validations;
}
