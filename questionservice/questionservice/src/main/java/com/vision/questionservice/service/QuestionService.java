package com.vision.questionservice.service;


import com.vision.questionservice.dao.QuestionsDao;
import com.vision.questionservice.model.QuestionWrapper;
import com.vision.questionservice.model.Questions;
import com.vision.questionservice.model.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionsDao questionsDao;

    public ResponseEntity<List<Questions>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionsDao.findAll(), HttpStatus.OK) ;
        } catch (Exception e) {
            
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST) ;
    }

    public ResponseEntity<List<Questions>> getQuestionByCategory(String category) {
        try{
            return new ResponseEntity<>(questionsDao.findByCategory(category), HttpStatus.OK) ;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST) ;
    }

    public ResponseEntity<String> saveQuestion(Questions questions) {
         questionsDao.save(questions);
         return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public ResponseEntity<String> removeQuestionById(int id) {
        questionsDao.deleteById(id);
        return new ResponseEntity<>("success",HttpStatus.FOUND);
    }

    public ResponseEntity<List<Integer>> generateQuestion(String category, int count, String level) {
        List<Integer> questionId=questionsDao.fetchRandomQuestionByCategory(category,level,count);

        return new ResponseEntity<>(questionId,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionByList(List<Integer> qid) {
        List<QuestionWrapper> questionWrappers= new ArrayList<>();

        for(int i:qid)
        {
            Questions q = questionsDao.findById(i).orElse(new Questions());
            QuestionWrapper qw = new QuestionWrapper(q);
            questionWrappers.add(qw);
        }

        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    private QuestionWrapper convertQuestionToWrapper(Questions q) {
        QuestionWrapper qw = new QuestionWrapper(q);
        qw.setId(q.getId());
        qw.setQuestionTitle(q.getQuestionTitle());
        qw.setOption1(q.getOption1());
        qw.setOption2(q.getOption2());
        qw.setOption3(q.getOption3());
        qw.setOption4(q.getOption4());
        qw.setDifficultyLevel(q.getDifficultyLevel());

        return qw;
    }

    public ResponseEntity<Integer> fetchScore(List<QuizResponse> response) {
        int score = 0;

        for(QuizResponse q : response)
        {
            Questions ques = questionsDao.findById(q.getId()).orElse(new Questions());
            if(q.getResponse().equalsIgnoreCase(ques.getRightAnswer()))
            {
                score++;
            }
        }

        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
