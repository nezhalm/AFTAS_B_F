package com.quiz.api.DTOs.StudentDTO;
import com.quiz.api.DTOs.AssignQuizDTO.AssignQuizDTO;
import com.quiz.api.models.Person;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class StudentResponseDTO extends Person {
    private Integer id;
    private LocalDate registeredAt;
    private List<AssignQuizDTO> AssignQuiz;

}
