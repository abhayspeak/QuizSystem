package com.vision.questionservice.controller;

import com.vision.questionservice.model.QuestionWrapper;
import com.vision.questionservice.model.Questions;
import com.vision.questionservice.model.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private com.vision.questionservice.service.QuestionService questionService;


    @GetMapping("allQuestions")
    public ResponseEntity<List<com.vision.questionservice.model.Questions>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<com.vision.questionservice.model.Questions>> getByCategory(@PathVariable String category) {
        return questionService.getQuestionByCategory(category);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        return questionService.removeQuestionById(id);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody com.vision.questionservice.model.Questions questions) {
        return questionService.saveQuestion(questions);
    }

    @PutMapping("update")
    public ResponseEntity<String> updateQuestion(@RequestBody Questions questions) {
        return questionService.saveQuestion(questions);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> generateQuestion(@RequestParam String category,@RequestParam String level, @RequestParam int count)
    {
        return questionService.generateQuestion(category,count,level);
    }

    @PostMapping("getquestion")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByList(@RequestBody List<Integer> qid)
    {
        return questionService.getQuestionByList(qid);
    }

    @PostMapping("score")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuizResponse> qr)
    {
        return questionService.fetchScore(qr);
    }
}
