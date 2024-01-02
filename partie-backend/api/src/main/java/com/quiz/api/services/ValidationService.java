package com.quiz.api.services;

import com.quiz.api.DTOs.TrainerDTO.*;
import com.quiz.api.DTOs.ValidationDTO.*;
import com.quiz.api.models.Validation;
import com.quiz.api.repositories.QuestionRepository;
import com.quiz.api.repositories.ResponseRepository;
import com.quiz.api.repositories.ValidationRepository;
import jakarta.transaction.Transactional.TxType;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;


@Service
public class ValidationService {

    private final ValidationRepository validationRepository;
    private final QuestionRepository questionRepository;
    private final ResponseRepository responseRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public ValidationService(ValidationRepository validationRepository, QuestionRepository questionRepository, ResponseRepository responseRepository, ModelMapper mapper) {
        this.validationRepository = validationRepository;
        this.questionRepository = questionRepository;
        this.responseRepository = responseRepository;
        modelMapper = mapper;
    }
    @Transactional
    public boolean existsQuestionAndResponse(Integer questionId, Integer responseId) {
        return validationRepository.existsByQuestion_IdAndResponse_Id(questionId, responseId);
    }


    public ValidationResponseDTO save(ValidationDTO validationDTO) {
        Validation validation = modelMapper.map(validationDTO, Validation.class);
        validation.setQuestion(questionRepository.findById(validationDTO.getQuestionId()).get());
        validation.setResponse(responseRepository.findById(validationDTO.getResponseId()).get());
        return modelMapper.map(validationRepository.save(validation), ValidationResponseDTO.class);
    }

    public void delete(Integer id) {
        validationRepository.deleteById(id);
    }

    public TrainerResponseDTO findById(Integer id) {
        Validation validation = modelMapper.map(validationRepository.findById(id).get(), Validation.class);
        return modelMapper.map(validation, TrainerResponseDTO.class);
    }

    public ValidationResponseDTO update(ValidationDTO validationDTO) {
        Validation validation = modelMapper.map(validationDTO, Validation.class);
        validation.setQuestion(questionRepository.findById(validationDTO.getQuestionId()).get());
        validation.setResponse(responseRepository.findById(validationDTO.getResponseId()).get());
        return modelMapper.map(validationRepository.save(validation), ValidationResponseDTO.class);
    }

    public List<ValidationResponseDTO> findAll() {
        return Arrays.asList(modelMapper
                .map(validationRepository.findAll(), ValidationResponseDTO[].class));
    }


}
