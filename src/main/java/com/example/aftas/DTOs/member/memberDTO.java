package com.example.aftas.DTOs.member;
import com.example.aftas.enums.IdentityDocumentType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;
@Data
public class memberDTO {
    private Integer num;
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Family name cannot be blank")
    private String familyName;

    @FutureOrPresent(message = "Date must be today")
    private LocalDate accessionDate;

    @NotBlank(message = "Nationality cannot be blank")
    private String nationality;

    @NotNull(message = "Identity document type cannot be null")
    private IdentityDocumentType identityDocument;

    @NotBlank(message = "Identity number cannot be blank")
    @Size(min = 1, max = 5, message = "Identity number must be between 1 and 5 characters")
    private String identityNumber;
}
