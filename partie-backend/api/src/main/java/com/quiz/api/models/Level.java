package com.quiz.api.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@NoArgsConstructor
@Data
@Entity
@Validated
public class Level {
    @Id
    @GeneratedValue
    private Integer id;
    private String description;
    private Integer maxPoints;
    private Integer minPoints;
    @OneToMany(mappedBy = "level", fetch = FetchType.LAZY)
    private List<Question> questions;
}
