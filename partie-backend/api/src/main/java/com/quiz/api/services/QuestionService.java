package com.quiz.api.services;
import com.quiz.api.DTOs.QuestionDTO.QuestionDTO;
import com.quiz.api.DTOs.QuestionDTO.QuestionResponseDTO;
import com.quiz.api.enums.ResponseType;
import com.quiz.api.models.Question;
import com.quiz.api.repositories.LevelRepository;
import com.quiz.api.repositories.QuestionRepository;
import com.quiz.api.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service

public class QuestionService {
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;
    private final LevelRepository levelRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, ModelMapper mapper, LevelRepository levelRepository, SubjectRepository subjectRepository) {
        this.questionRepository = questionRepository;
        this.modelMapper = mapper;
        this.levelRepository = levelRepository;
        this.subjectRepository = subjectRepository;

    }
    public List<QuestionResponseDTO> getAll() {
        return Arrays.asList(modelMapper.map(questionRepository.findAll(), QuestionResponseDTO[].class));
    }
    public QuestionResponseDTO save(QuestionDTO questionDTO) {
        Question question = modelMapper.map(questionDTO, Question.class);
        question.setType(ResponseType.SINGLE);
        question.setLevel(levelRepository.findById(questionDTO.getLevelId()).get());
        question.setSubject(subjectRepository.findById(questionDTO.getSubjectId()).get());
        return modelMapper.map(questionRepository.save(question), QuestionResponseDTO.class);
    }

    public void delete(Integer id) {
        questionRepository.deleteById(id);
    }


    public QuestionResponseDTO update(QuestionDTO questionDTO) {
        Question question = modelMapper.map(questionDTO, Question.class);
        question.setType(ResponseType.SINGLE);
        question.setLevel(levelRepository.findById(questionDTO.getLevelId()).get());
        question.setSubject(subjectRepository.findById(questionDTO.getSubjectId()).get());
        return modelMapper.map(questionRepository.save(question), QuestionResponseDTO.class);
    }

    public boolean existsById(Integer id){
        return questionRepository.existsById(id);
    }
    public QuestionResponseDTO getQuestionById(Integer id) {
        Question question = modelMapper.map(questionRepository.findById(id).get(), Question.class);
        return modelMapper.map(question, QuestionResponseDTO.class);
    }

    public List<QuestionResponseDTO> questionsByLevelId(Integer levelId) {
        return Arrays.asList(modelMapper.map(questionRepository.findAllByLevelId(levelId), QuestionResponseDTO[].class));
    }

    public List<QuestionResponseDTO> questionsBySubjectId(Integer subjectId) {
        return Arrays.asList(modelMapper.map(questionRepository.findAllBySubjectId(subjectId), QuestionResponseDTO[].class));
    }
    //find questions bu level
    public List<Question> getQuestionsByLevel(Integer levelId) {
        if (levelId == null) {
            throw new IllegalArgumentException("L'ID du niveau ne peut pas être nul.");
        }

        return questionRepository.findByLevelId(levelId);
    }
}
