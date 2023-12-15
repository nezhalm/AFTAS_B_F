package com.example.aftas.controllers;

import com.example.aftas.DTOs.competition.CompetitionDTO;
import com.example.aftas.DTOs.level.levelDTO;
import com.example.aftas.services.CompetitionService;
import com.example.aftas.services.LevelService;
import com.example.aftas.services.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RequestMapping("/api/level")
@RestController
@CrossOrigin
public class levelController {
    private final LevelService levelService;

    @Autowired
    public levelController(LevelService level) {
        levelService = level;
    }


    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody levelDTO levelDTO) {
        Map<String, Object> result = new HashMap<>();
        try{
            result.put("competition", levelService.save(levelDTO));
            result.put("message", "competition created successfully");
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e) {
            result.put("error", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }
}
