package com.quiz.api.controllers;
import com.quiz.api.DTOs.SubjectDTO.SubjectDTO;
import com.quiz.api.models.Subject;
import com.quiz.api.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin

public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subject) {
        subjectService = subject;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> subjects() throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            if(subjectService.getAll().isEmpty()) {
                message.put("message", "No subjects found!");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
            message.put("message", "subjects found");
            message.put("subjects", subjectService.getAll());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            throw new Exception("cannot find any subject");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (subjectService.existsById(id)) {
                subjectService.delete(id);
                result.put("message", "subject with ID " + id + " has been deleted successfully");
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.put("error", "subject with ID " + id + " does not exist");
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            result.put("error", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Integer id, @RequestBody SubjectDTO subject) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (subjectService.existsById(id)) {
                subject.setId(id);
                subjectService.update(subject);
                result.put("message", "subject with ID " + id + " has been updated successfully");
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.put("error", "subject with ID " + id + " does not exist");
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            result.put("error", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@RequestBody SubjectDTO subjectDTO) throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            message.put("message", "subject created");
            message.put("subject", subjectService.save(subjectDTO));
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }catch(Exception e){
            throw new Exception("cannot create a new subject");
        }
    }


//
//    @PostMapping("/addWithParent/{parentId}")
//    public ResponseEntity<Subject> addSubjectWithParent(@PathVariable Integer parentId, @RequestBody Subject newSubject) {
//        Subject createdSubject = subjectService.add(newSubject, parentId);
//        if (createdSubject == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(createdSubject);
//    }








    @GetMapping("/by-parent-id")
    public ResponseEntity<List<Subject>> getSubjectsByParentId(@RequestParam Integer parentId) {
        List<Subject> subjects = subjectService.getSubjectsByParentId(parentId);

        if (subjects.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(subjects);
        }
    }
}
