package com.example.aftas.repositories;
import com.example.aftas.models.MemberCompetitionKey;
import com.example.aftas.models.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RankingRepository extends JpaRepository<Ranking, MemberCompetitionKey> {
      Ranking findById_CompetitionIdAndId_MemberId(String competitionId, Integer memberId);
     List<Ranking> findById_CompetitionId(String competitionId );
    @Query("SELECT COUNT(r) FROM Ranking r WHERE r.id.competitionId = :competitionId")
    long countRankingsByCompetitionId(@Param("competitionId") String competitionId);
}
