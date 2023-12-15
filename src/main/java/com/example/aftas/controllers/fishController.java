package com.example.aftas.controllers;

import com.example.aftas.services.FishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/fish")
@CrossOrigin(origins = "*")
public class fishController {
    private final FishService fishService;

    public fishController(FishService fishService) {
        this.fishService = fishService;
    }


    @GetMapping
    public ResponseEntity<Map<String, Object>> fish() throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            if(fishService.getAll().isEmpty()) {
                message.put("message", "No fish found!");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }        message.put("message", "fish found");
            message.put("fish", fishService.getAll());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            throw new Exception("cannot find any fish");
        }
    }
}
