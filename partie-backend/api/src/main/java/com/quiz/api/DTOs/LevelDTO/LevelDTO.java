package com.quiz.api.DTOs.LevelDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LevelDTO {

    private Integer id;

    @NotBlank(message = "La description ne peut pas être vide")
    private String description;

    @Min(value = 1, message = "maxPoints doit être supérieur ou égal à 0")
    private Integer maxPoints;

    @Min(value = 1, message = "minPoints doit être supérieur ou égal à 0")
    private Integer minPoints;

}
