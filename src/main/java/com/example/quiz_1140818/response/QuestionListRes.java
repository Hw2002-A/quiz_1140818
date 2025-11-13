package com.example.quiz_1140818.response;

import java.util.List;

import com.example.quiz_1140818.vo.QuestionVo;
import com.fasterxml.jackson.annotation.JsonAlias;

public class QuestionListRes extends BasicRes{
	
	@JsonAlias
	private List<QuestionVo> questionVoList;

	public QuestionListRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionListRes(int code, String message, boolean admin) {
		super(code, message, admin);
		// TODO Auto-generated constructor stub
	}

	public QuestionListRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public QuestionListRes(int code, String message, List<QuestionVo> questionVoList) {
		super(code, message);
		this.questionVoList = questionVoList;
	}

	public List<QuestionVo> getQuestionVoList() {
		return questionVoList;
	}

	public void setQuestionVoList(List<QuestionVo> questionVoList) {
		this.questionVoList = questionVoList;
	}
	
	

}
