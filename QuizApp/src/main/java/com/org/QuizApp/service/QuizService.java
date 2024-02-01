package com.org.QuizApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.org.QuizApp.dao.QuestionDao;
import com.org.QuizApp.dao.QuizDao;
import com.org.QuizApp.model.Question;
import com.org.QuizApp.model.QuestionWrapper;
import com.org.QuizApp.model.Quiz;
import com.org.QuizApp.model.Response;

@Service
public class QuizService {

	@Autowired
	QuizDao Quizdao;
	
	@Autowired
	QuestionDao Quesdao;

	public ResponseEntity<String> createQuiz(String category, int numQues, String title) {
		// TODO Auto-generated method stub
		List<Question> questions= Quesdao.getRandomQuestionByCategory(category,numQues) ; 
		Quiz q= new Quiz();
		q.setTitle(title);
		q.setQuestions(questions);
		Quizdao.save(q);
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		// TODO Auto-generated method stub
		Optional<Quiz> quiz= Quizdao.findById(id);
		List<Question> questionFromDb = quiz.get().getQuestions();
		List<QuestionWrapper> questionForUser= new ArrayList<>();
		for(Question q : questionFromDb ) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			questionForUser.add(qw);
		}
		return new ResponseEntity<>(questionForUser,HttpStatus.OK);
	}

	public ResponseEntity<Integer> fetchResult(Integer id, List<Response> res) {
		// TODO Auto-generated method stub
		Quiz quiz= Quizdao.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		int i=0;
		int right=0;
		for(Response response : res) {
			if(response.getResponse().equals(questions.get(i).getSolution())) {
				right++;
			}
			i++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

}
