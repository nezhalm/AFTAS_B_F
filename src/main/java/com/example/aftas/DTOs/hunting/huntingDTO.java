package com.example.aftas.DTOs.hunting;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


    @Data
    public class huntingDTO {
        @NotNull(message = "Id cannot be null")
        private Long id;

        @Min(value = 1, message = "Number of fish must be at least 1")
        private int numberOfFish;

        @NotBlank(message = "Fish Id cannot be blank")
        private String fishId;

        @NotNull(message = "Member Id cannot be null")
        @Min(value = 1, message = "Member Id must be at least 1")
        private Integer memberId;

        @NotBlank(message = "Competition Id cannot be blank")
        private String competitionId;
    }


