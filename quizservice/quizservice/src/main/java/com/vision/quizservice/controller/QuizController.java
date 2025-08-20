package com.vision.quizservice.controller;


import com.vision.quizservice.model.QuestionWrapper;
import com.vision.quizservice.model.Quiz;
import com.vision.quizservice.model.QuizDTO;
import com.vision.quizservice.model.QuizResponse;
import com.vision.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<List<Integer>> createQuiz(@RequestBody QuizDTO quizDTO) {
        return quizService.createQuiz(quizDTO);
    }

    @GetMapping("quiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
        return quizService.fetchQuizDetails(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> getSubmitQuiz(@PathVariable int id, @RequestBody List<QuizResponse> response){
        return quizService.submitQuizAnwer(id,response);
    }

}
