package com.example.aftas.DTOs.level;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class levelDTO {

    @NotNull(message = "Id cannot be null")
    private Long code;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @Positive(message = "Points must be a positive number")
    private int points;
}