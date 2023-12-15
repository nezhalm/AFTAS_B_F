package com.example.aftas.DTOs.competition;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;
@Data
    @NoArgsConstructor
    public class CompetitionDTO {
    @FutureOrPresent(message = "Date must be today or in the future")
    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @NotNull(message = "Start time cannot be null")
    private LocalTime startTime;

    @NotNull(message = "End time cannot be null")
    private LocalTime endTime;

    @Min(value = 1, message = "Number of participants must be at least 1")
    private int numberOfParticipants;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @Min(value = 0, message = "Amount cannot be negative")
    private Integer amount;
}



