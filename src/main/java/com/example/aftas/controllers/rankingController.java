package com.example.aftas.controllers;
import com.example.aftas.DTOs.ranking.RankingResponseDTO;
import com.example.aftas.services.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/ranking")
@CrossOrigin
public class rankingController {
    private final RankingService rankingService;

    @Autowired
    public rankingController(RankingService ranking) {
        rankingService = ranking;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getRanking(@RequestParam String competitionCode) {
        Map<String, Object> message = new HashMap<>();
        try {
            List<RankingResponseDTO> rankingResponseDTOs = rankingService.getMembersWithSpecificCompetition(competitionCode);
            if (rankingResponseDTOs.isEmpty()) {
                message.put("message", "No ranking found!");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
            message.put("message", "ranking found");
            message.put("ranking", rankingResponseDTOs);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            message.put("message", "Cannot find any ranking");
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
