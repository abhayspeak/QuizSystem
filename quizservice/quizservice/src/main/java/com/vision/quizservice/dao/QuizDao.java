package com.vision.quizservice.dao;

import com.vision.quizservice.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
