package com.mvp.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mvp.question.models.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {

}
