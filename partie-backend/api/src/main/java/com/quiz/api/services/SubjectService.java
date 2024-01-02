package com.quiz.api.services;

import com.quiz.api.DTOs.SubjectDTO.SubjectDTO;
import com.quiz.api.DTOs.SubjectDTO.SubjectResponseDTO;
import com.quiz.api.models.Student;
import com.quiz.api.models.Subject;
import com.quiz.api.repositories.StudentRepository;
import com.quiz.api.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SubjectService(SubjectRepository repository, ModelMapper mapper) {
        subjectRepository = repository;
        modelMapper = mapper;
    }

    public List<SubjectResponseDTO> getAll() {
        return Arrays.asList(modelMapper.map(subjectRepository.findAll(), SubjectResponseDTO[].class));

    }

    public SubjectResponseDTO save(SubjectDTO subjectDTO) {
        Subject subject = modelMapper.map(subjectDTO, Subject.class);
        subject.setParent(subjectRepository.findById(subjectDTO.getParentId()).get());
        return modelMapper.map(subjectRepository.save(subject), SubjectResponseDTO.class);
    }



    public void delete(Integer id) {
        subjectRepository.deleteById(id);
    }


    public SubjectResponseDTO update(SubjectDTO subjectDTO) {
        Subject subject = modelMapper.map(subjectDTO,Subject.class);
        subjectRepository.save(subject);
        return modelMapper.map(subjectRepository.save(subject),SubjectResponseDTO.class);
    }


    public boolean existsById(Integer id){
        return subjectRepository.existsById(id);
    }
    public Subject getParentById(Integer parentId) {
        return subjectRepository.findById(parentId).orElse(null);
    }



    public Subject add(Subject newSubject, Integer parentId) {
        Subject parent = subjectRepository.findById(parentId).orElse(null);
        if (parent == null) {
            return null;
        }
        newSubject.setParent(parent);
        parent.getChildren().add(newSubject);
        subjectRepository.save(parent);
        return subjectRepository.save(newSubject);
    }


    public List<Subject> getSubjectsByParentId(Integer parentId) {
        return subjectRepository.findAllByParentId(parentId);
    }

}
