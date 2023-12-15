package com.example.aftas.controllers;
import com.example.aftas.DTOs.competition.CompetitionDTO;
import com.example.aftas.DTOs.ranking.RankingDTO;
import com.example.aftas.DTOs.ranking.RankingResponseDTO;
import com.example.aftas.models.Competition;
import com.example.aftas.services.CompetitionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping("/api/competition")
@CrossOrigin(origins = "*")
public class competitionController {
    private final CompetitionService competitionService;

    @Autowired
    public competitionController(CompetitionService completion) {
        competitionService = completion;
    }




    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody CompetitionDTO competitionDTO) {
        Map<String, Object> result = new HashMap<>();
        try{
            result.put("competition", competitionService.create(competitionDTO));
            result.put("message", "competition created successfully");
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e) {
            result.put("error", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/reservePlace")
    public ResponseEntity<Map<String, Object>> reserve(@RequestBody RankingDTO rankingDTO) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("reservation", competitionService.reserve(rankingDTO));
            result.put("message", "Reservation created successfully");
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            result.put("error", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }





    @GetMapping
    public ResponseEntity<Map<String, Object>> competitions(@RequestParam int page, @RequestParam int items) throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            if(competitionService.getAll(page,items).isEmpty()) {
                message.put("message", "No competitions found!");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
            message.put("message", "competitions found");
            message.put("competitions", competitionService.getAll(page,items));
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            throw new Exception("cannot find any level");
        }
    }



    @GetMapping("/All")
    public ResponseEntity<Map<String, Object>> competitions() throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            if(competitionService.getAllWithoutPagination().isEmpty()) {
                message.put("message", "No competitions found!");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
            message.put("message", "competitions found");
            message.put("competitions", competitionService.getAllWithoutPagination());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            throw new Exception("cannot find any level");
        }
    }
//    @GetMapping
//    public ResponseEntity<Map<String, Object>> members() throws Exception {
//        Map<String, Object> message = new HashMap<>();
//        try{
//            if(competitionService.getAll().isEmpty()) {
//                message.put("message", "No competitions found!");
//                return new ResponseEntity<>(message, HttpStatus.OK);
//            }
//            message.put("message", "competitions found");
//            message.put("members", competitionService.getAll());
//            return new ResponseEntity<>(message, HttpStatus.OK);
//        }catch(Exception e){
//            throw new Exception("cannot find any member");
//        }
//    }
















//    @DeleteMapping("/{id}")
//    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
//        Map<String, Object> result = new HashMap<>();
//        try {
//            if (levelService.existsById(id)) {
//                levelService.delete(id);
//                result.put("message", "Level with ID " + id + " has been deleted successfully");
//                return new ResponseEntity<>(result, HttpStatus.OK);
//            } else {
//                result.put("error", "Level with ID " + id + " does not exist");
//                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            result.put("error", e.getMessage());
//            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Map<String, Object>> update(@PathVariable Integer id, @RequestBody LevelDTO subject) {
//        Map<String, Object> result = new HashMap<>();
//        try {
//            if (levelService.existsById(id)) {
//                subject.setId(id);
//                levelService.update(subject);
//                result.put("message", "level with ID " + id + " has been updated successfully");
//                return new ResponseEntity<>(result, HttpStatus.OK);
//            } else {
//                result.put("error", "level with ID " + id + " does not exist");
//                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            result.put("error", e.getMessage());
//            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
