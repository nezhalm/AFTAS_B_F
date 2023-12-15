package com.example.aftas.DTOs.ranking;
import com.example.aftas.DTOs.competition.CompetitionDTO;
import com.example.aftas.DTOs.member.memberDTO;
import com.example.aftas.models.Competition;
import com.example.aftas.models.Member;
import lombok.Data;

@Data
    public class RankingResponseDTO {
        private memberDTO member;
        private CompetitionDTO competition;
        private Integer rank;
        private Integer score;
    }

