package com.vision.questionservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizResponse {
    private int id;
    private String response;
}
