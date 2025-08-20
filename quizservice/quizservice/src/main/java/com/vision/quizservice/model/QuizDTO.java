package com.vision.quizservice.model;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class QuizDTO {

    private String category;
    private int count;
    private String level;
    private String title;
}
