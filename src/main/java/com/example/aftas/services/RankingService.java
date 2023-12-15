package com.example.aftas.services;
import com.example.aftas.DTOs.ranking.RankingResponseDTO;
import com.example.aftas.models.Ranking;
import com.example.aftas.repositories.RankingRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.lang.reflect.Type;
import java.util.List;

@Service
public class RankingService {
    private final RankingRepository rankingRepository;
    private final ModelMapper modelMapper;




    public RankingService(RankingRepository rankingRepository, ModelMapper modelMapper) {
        this.rankingRepository = rankingRepository;
        this.modelMapper = modelMapper;
    }

    public List<RankingResponseDTO> getMembersWithSpecificCompetition(String competitionCode) {
        List<Ranking> rankings = rankingRepository.findById_CompetitionId(competitionCode);
        Type listType = new TypeToken<List<RankingResponseDTO>>() {}.getType();
        return modelMapper.map(rankings, listType);
    }



}
