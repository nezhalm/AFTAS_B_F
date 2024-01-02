package com.quiz.api.DTOs.StudentDTO;

import com.quiz.api.models.Person;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class StudentDTO extends Person {

    private Integer id;
    private LocalDate registeredAt;

}