package com.quiz.api.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
@Entity
public class Validation {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer points;
    @ManyToOne
    @JoinColumn(name = "question_id",referencedColumnName = "id")
    private Question question;
    @ManyToOne
    @JoinColumn(name = "response_id",referencedColumnName = "id")
    private Response response;

}