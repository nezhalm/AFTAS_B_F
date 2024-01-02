package com.quiz.api.DTOs.SubjectDTO;
import lombok.Data;
import lombok.NoArgsConstructor;



    @Data
    @NoArgsConstructor
    public class SubjectDTO {

        private Integer id;
        private String title;
        private Integer parentId;

    }


