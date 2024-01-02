package com.quiz.api.DTOs.TrainerDTO;

import com.quiz.api.models.Person;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrainerDTO extends Person {

    private Integer id;
    private String speciality;

}

