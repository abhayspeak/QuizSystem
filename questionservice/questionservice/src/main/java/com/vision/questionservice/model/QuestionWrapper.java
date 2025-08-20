package com.vision.questionservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class QuestionWrapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String difficultyLevel;

    public QuestionWrapper(Questions q)
    {
        this.id = q.getId();
        this.questionTitle = q.getQuestionTitle();
        this.option1 = q.getOption1();
        this.option2 = q.getOption2();
        this.option3 = q.getOption3();
        this.option4 = q.getOption4();
        this.difficultyLevel = q.getDifficultyLevel();
    }

}
