package com.quiz.api.controllers;
import com.quiz.api.DTOs.QuestionDTO.QuestionDTO;
import com.quiz.api.models.Question;
import com.quiz.api.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin

public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService service) {
        questionService = service;
    }

    @GetMapping
        public ResponseEntity<Map<String, Object>> questions() throws Exception {
            Map<String, Object> message = new HashMap<>();
            try{
                if(questionService.getAll().isEmpty()) {
                    message.put("message", "No questions found!");
                    return new ResponseEntity<>(message, HttpStatus.OK);
                }
                message.put("message", "questions found");
                message.put("questions", questionService.getAll());
                return new ResponseEntity<>(message, HttpStatus.OK);
            }catch(Exception e){
                throw new Exception("cannot find any level");
            }

    }

    @PostMapping()
    public ResponseEntity<Map<String, Object>> save(@RequestBody QuestionDTO questionDTO) throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            message.put("message", "question created");
            message.put("subject", questionService.save(questionDTO));
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }catch(Exception e){
            message.put("subject", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

    }





    @GetMapping("/{questionId}")
    public ResponseEntity<Map<String, Object>> getQuestionById(@PathVariable Integer questionId) {
        Map<String, Object> result = new HashMap<>();
        try {
            if(questionService.getQuestionById(questionId) == null) {
                result.put("message", "Question with questionId "+questionId+" not found!");
                return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }
            result.put("question", questionService.getQuestionById(questionId));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("message", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }









    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (questionService.existsById(id)) {
                questionService.delete(id);
                result.put("message", "Question with ID " + id + " has been deleted successfully");
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.put("error", "Question with ID " + id + " does not exist");
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            result.put("error", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Integer id, @RequestBody QuestionDTO subject) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (questionService.existsById(id)) {
                subject.setId(id);
                questionService.update(subject);
                result.put("message", "question with ID " + id + " has been updated successfully");
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.put("error", "question with ID " + id + " does not exist");
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            result.put("error", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping("/by-level/{levelId}")
    public Map<String, Object> getQuestionsByLevel(@PathVariable(required = false) Integer levelId) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Si le niveau n'est pas fourni, renvoyer une mauvaise demande
            if (levelId == null) {
                response.put("error", "Le niveau est requis.");
                return response;
            }

            List<Question> questions = questionService.getQuestionsByLevel(levelId);

            if (questions.isEmpty()) {
                response.put("message", "Aucune question trouvée pour le niveau spécifié.");
            } else {
                response.put("questions", questions);
            }

        } catch (IllegalArgumentException e) {
            response.put("error", "Erreur : " + e.getMessage());
        } catch (Exception e) {
            response.put("error", "Erreur interne du serveur : " + e.getMessage());
        }

        return response;
    }

    @GetMapping("/GetQuestionBYSubjectId/{id}")
    public ResponseEntity<Map<String, Object>> getQuestionsBySubjectID(@PathVariable Integer id) throws Exception {

        Map<String, Object> message = new HashMap<>();

        try{
            if(questionService.questionsBySubjectId(id).isEmpty()) {
                message.put("message", "No questions found for this subject!");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
            message.put("message", "questions found");
            message.put("questions", questionService.questionsBySubjectId(id));
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            throw new Exception("cannot find any question");
        }

    }

    @GetMapping("/GetQuestionBYLevelId/{id}")
    public ResponseEntity<Map<String, Object>> getQuestionsByLevelID(@PathVariable Integer id) throws Exception {

        Map<String, Object> message = new HashMap<>();

        try{
            if(questionService.questionsByLevelId(id).isEmpty()) {
                message.put("message", "No questions found for this level!");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
            message.put("message", "questions found");
            message.put("questions", questionService.questionsByLevelId(id));
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            throw new Exception("cannot find any question");
        }

    }

}
