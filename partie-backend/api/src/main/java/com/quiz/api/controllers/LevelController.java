package com.quiz.api.controllers;
import com.quiz.api.DTOs.LevelDTO.LevelDTO;
import com.quiz.api.services.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping("/api/levels")
@CrossOrigin
public class LevelController {
    private final LevelService levelService;

    @Autowired
    public LevelController(LevelService level) {
        levelService = level;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> levels() throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            if(levelService.getAll().isEmpty()) {
                message.put("message", "No levels found!");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
            message.put("message", "levels found");
            message.put("levels", levelService.getAll());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            throw new Exception("cannot find any level");
        }
    }



    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody LevelDTO level) {
        Map<String, Object> result = new HashMap<>();
        try{
            result.put("level", levelService.save(level));
            result.put("message", "level created successfully");
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e) {
            result.put("error", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (levelService.existsById(id)) {
                levelService.delete(id);
                result.put("message", "Level with ID " + id + " has been deleted successfully");
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.put("error", "Level with ID " + id + " does not exist");
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            result.put("error", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Integer id, @RequestBody LevelDTO subject) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (levelService.existsById(id)) {
                subject.setId(id);
                levelService.update(subject);
                result.put("message", "level with ID " + id + " has been updated successfully");
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.put("error", "level with ID " + id + " does not exist");
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            result.put("error", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
