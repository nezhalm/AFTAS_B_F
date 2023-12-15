package com.example.aftas.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
@Entity
@Validated
@NoArgsConstructor
@Data
public class Ranking {
    @EmbeddedId
    private MemberCompetitionKey id;
    private Integer rank;
    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num", insertable = false, updatable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_code", insertable = false, updatable = false)
    private Competition competition;
}
