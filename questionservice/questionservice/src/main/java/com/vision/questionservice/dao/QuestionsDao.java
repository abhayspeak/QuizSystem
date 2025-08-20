package com.vision.questionservice.dao;

import com.vision.questionservice.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsDao extends JpaRepository<Questions,Integer> {
    List<Questions> findByCategory(String category);

    @Query(value = "Select q.id from questions q where category=:category and difficulty_level=:level order by RANDOM() limit :noOfQuestion", nativeQuery = true)
    List<Integer> fetchRandomQuestionByCategory(String category, String level, int noOfQuestion);
}
