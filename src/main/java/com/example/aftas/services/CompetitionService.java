package com.example.aftas.services;
import com.example.aftas.DTOs.competition.CompetitionDTO;
import com.example.aftas.DTOs.competition.CompetitionResponseDTO;
import com.example.aftas.DTOs.ranking.RankingDTO;
import com.example.aftas.DTOs.ranking.RankingResponseDTO;
import com.example.aftas.models.Competition;
import com.example.aftas.models.Member;
import com.example.aftas.models.MemberCompetitionKey;
import com.example.aftas.models.Ranking;
import com.example.aftas.repositories.CompetitionRepository;
import com.example.aftas.repositories.MemberRepository;
import com.example.aftas.repositories.RankingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final RankingRepository rankingRepository;

    private final MemberRepository memberRepository;


    private final ModelMapper modelMapper;

    @Autowired
    public CompetitionService(RankingRepository RRepository, CompetitionRepository repository, MemberRepository memberRepository, ModelMapper mapper) {
        rankingRepository = RRepository;
        competitionRepository = repository;
        this.memberRepository = memberRepository;
        modelMapper = mapper;
    }

    public CompetitionDTO create(CompetitionDTO competitionDTO) {
        if (competitionRepository.existsByLocationAndDate(competitionDTO.getLocation(), competitionDTO.getDate())) {
            throw new RuntimeException("Une compétition avec la même location et la même date existe déjà.");
        }
        Competition competition = modelMapper.map(competitionDTO, Competition.class);
        String generatedCode = generateCode(competitionDTO.getDate(), competitionDTO.getLocation());
        if (isValidCode(generatedCode)) {
            competition.setCode(generatedCode);
            competitionRepository.save(competition);
            return competitionDTO;
        } else {
            throw new RuntimeException("Le code généré n'est pas valide.");
        }
    }

    public List<CompetitionResponseDTO> getAll(int pageNumber, int pageSize) {
        Pageable pages = PageRequest.of(pageNumber, pageSize);
        Page<Competition> competitionsPage = competitionRepository.findAll(pages);
        return competitionsPage
                .stream()
                .map(competition -> modelMapper.map(competition, CompetitionResponseDTO.class))
                .collect(Collectors.toList());
    }

    public List<CompetitionResponseDTO> getAllWithoutPagination() {
        return Arrays.asList(modelMapper.map(competitionRepository.findAll(), CompetitionResponseDTO[].class));
    }


    public RankingResponseDTO reserve(RankingDTO rankingDTO) {
        Competition competition = competitionRepository.findById(rankingDTO.getCompetitionId())
                .orElseThrow(() -> new RuntimeException("La compétition n'existe pas"));

        long reservedRanks = rankingRepository.countRankingsByCompetitionId(rankingDTO.getCompetitionId());
        int numberOfParticipants = competition.getNumberOfParticipants();

        if (reservedRanks < numberOfParticipants) {
            Member member = memberRepository.findByNum(rankingDTO.getMemberId());

            Ranking ranking = new Ranking();
            ranking.setId(new MemberCompetitionKey(member, competition));
            ranking.setRank(rankingDTO.getRank());
            ranking.setScore(rankingDTO.getScore());
            rankingRepository.save(ranking);

            return modelMapper.map(ranking, RankingResponseDTO.class);
        } else {
            throw new RuntimeException("Aucune place disponible pour cette compétition");
        }
    }



    private void validateCompetitionDate(String competitionCode) {
        LocalDate today = LocalDate.now();
        Competition competition = competitionRepository.findById(competitionCode)
                .orElseThrow(() -> new RuntimeException("La compétition n'existe pas"));
        if (ChronoUnit.DAYS.between(today, competition.getDate()) < 1) {
            throw new RuntimeException("La date n'est pas valide pour cette compétition");
        }
    }

    private boolean validateCompetitionDates(String competitionId) {
        Competition competition = competitionRepository.findById(competitionId).orElse(null);
        if (competition != null) {
            LocalDate inscriptionDate = LocalDate.now();
            LocalDate competitionStartDate = competition.getDate();
            long daysBetween = ChronoUnit.DAYS.between(inscriptionDate, competitionStartDate);
            return daysBetween >= 1;
        } else {
            return false;
        }
    }

    private String generateCode(LocalDate date, String location) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yy"));
        String locationCode = location.substring(0, Math.min(location.length(), 3)).toLowerCase();
        return locationCode + "-" + formattedDate;
    }
    private boolean isValidCode(String code) {
        String pattern = "^[a-z]{3}-\\d{2}-\\d{2}-\\d{2}$";
        return Pattern.matches(pattern, code);
    }







}