package com.quiz.api.models;
import com.quiz.api.enums.ResponseType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table
public class Question {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer numberOfResponses;

    private Integer numberOfCorrectResponses;

    private String content;

    @Enumerated(EnumType.STRING)
    private ResponseType type;

    private Integer points;

    @ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "id")
    private Level level;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Validation> validations;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Media> medias;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<QuizzQuestion> quizQuestions;
}
