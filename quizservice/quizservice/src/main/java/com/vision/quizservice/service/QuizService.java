package com.vision.quizservice.service;

import com.vision.quizservice.dao.QuizDao;
import com.vision.quizservice.feige.QuestionServiceClient;
import com.vision.quizservice.model.QuestionWrapper;
import com.vision.quizservice.model.Quiz;
import com.vision.quizservice.model.QuizDTO;
import com.vision.quizservice.model.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuestionServiceClient questionServiceClient;


    public ResponseEntity<List<Integer>> createQuiz(QuizDTO quizDTO) {

        List<Integer> questions= questionServiceClient.generateQuestion(quizDTO.getCategory(),quizDTO.getLevel(),quizDTO.getCount()).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(quizDTO.getTitle());
        quiz.setLevel(quizDTO.getLevel());
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>(questions, HttpStatus.CREATED);

    }


    public ResponseEntity<Integer> submitQuizAnwer(int id, List<QuizResponse> response) {
        return new ResponseEntity<>(questionServiceClient.getScore(response).getBody(),HttpStatus.OK);
    }


    public ResponseEntity<List<QuestionWrapper>> fetchQuizDetails(Integer id) {
        Quiz quiz =quizDao.findById(id).orElse(new Quiz());
        List<Integer> qid=quiz.getQuestions();
        return questionServiceClient.getQuestionsByList(qid);
    }
}
