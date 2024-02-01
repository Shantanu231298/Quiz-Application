package com.org.QuizApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.QuizApp.model.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
