package com.example.quiz_1140818.response;

import java.util.List;

import com.example.quiz_1140818.entity.Quiz;

public class QuizListRes extends BasicRes{
	
	
	private List<Quiz> quizList;

	public QuizListRes() {
		super();
		
	}

	public QuizListRes(int code, String message, boolean admin) {
		super(code, message, admin);
		
	}

	public QuizListRes(int code, String message) {
		super(code, message);
		
	}

	public QuizListRes(int code, String message, List<Quiz> quizList) {
		super(code, message);
		this.quizList = quizList;
	}

	public List<Quiz> getQuizList() {
		return quizList;
	}

	public void setQuizList(List<Quiz> quizList) {
		this.quizList = quizList;
	}
	

}
