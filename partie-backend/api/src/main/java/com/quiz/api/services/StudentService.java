package com.quiz.api.services;
import com.quiz.api.DTOs.StudentDTO.*;
import com.quiz.api.models.Student;
import com.quiz.api.models.Subject;
import com.quiz.api.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public StudentService(StudentRepository studentRepository, ModelMapper mapper) {
        this.studentRepository = studentRepository;
        modelMapper = mapper;
    }

    public StudentResponseDTO save(StudentDTO studentDTO) {
        Student student = modelMapper.map(studentDTO, Student.class);
        return modelMapper.map(studentRepository.save(student), StudentResponseDTO.class);
    }

    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }

    public StudentResponseDTO findById(Integer id) {
        Student student = modelMapper.map(studentRepository.findById(id).get(), Student.class);
        return modelMapper.map(student, StudentResponseDTO.class);
    }

    public StudentResponseDTO update(StudentDTO studentDTO) {
        Student student = modelMapper.map(studentDTO, Student.class);
        return modelMapper.map(studentRepository.save(student), StudentResponseDTO.class);
    }

    public List<StudentResponseDTO> findAll(int pageNumber, int pageSize) {
        Pageable pages = PageRequest.of(pageNumber, pageSize);
        Page<Student> subjectsPage = studentRepository.findAll(pages);
        return subjectsPage
                .stream()
                .map(student -> modelMapper.map(student, StudentResponseDTO.class))
                .collect(Collectors.toList());
    }
}


