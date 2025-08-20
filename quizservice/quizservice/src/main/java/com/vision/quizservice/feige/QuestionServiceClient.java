package com.vision.quizservice.feige;

import com.vision.quizservice.model.QuestionWrapper;
import com.vision.quizservice.model.QuizResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTIONSERVICE")
public interface QuestionServiceClient {
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> generateQuestion(@RequestParam String category, @RequestParam String level, @RequestParam int count);

    @PostMapping("question/getquestion")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByList(@RequestBody List<Integer> qid);

    @PostMapping("question/score")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuizResponse> qr);
}
