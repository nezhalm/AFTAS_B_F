package com.example.aftas.models;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import java.io.Serializable;
@Validated
@NoArgsConstructor
@Data
@Embeddable
public class MemberCompetitionKey  implements Serializable {

        @Column(name = "member_num", nullable = false)
        private Integer memberId;

        @Column(name = "competition_code", nullable = false)
        private String competitionId;


    public MemberCompetitionKey(Member member, Competition competition) {
        this.memberId = member.getNum();  // Assurez-vous que vous avez une méthode getId() dans la classe Member
        this.competitionId = competition.getCode();  // Assurez-vous que vous avez une méthode getId() dans la classe Competition
    }
}


