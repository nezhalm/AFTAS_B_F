package com.quiz.api.models;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import java.time.Duration;
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Person {
    private String firstname;
    private String lastname;
    @Temporal(TemporalType.DATE)
    private Duration birthdate;
    private String address;
}
