package com.org.QuizApp.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.org.QuizApp.dao.QuestionDao;
import com.org.QuizApp.model.Question;

@Service
public class QuestionService {

	@Autowired
	private QuestionDao dao;

	public ResponseEntity<List<Question>> getAllQuestions() {
		// TODO Auto-generated method stub
		try {
		return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		// TODO Auto-generated method stub
		
		try {
			return new ResponseEntity<>(dao.findByCategory(category), HttpStatus.OK);
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}

	public ResponseEntity<String> addQuestion(Question question) {
		// TODO Auto-generated method stub
		dao.save(question);
		try {
			return new ResponseEntity<>("Success",HttpStatus.CREATED);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
	}
	public ResponseEntity<String> deleteQuestion(int id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
		try {
			return new ResponseEntity<>("Deleted",HttpStatus.ACCEPTED);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
	}
	
	
	
}
