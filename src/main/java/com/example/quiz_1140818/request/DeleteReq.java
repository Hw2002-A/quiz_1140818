package com.example.quiz_1140818.request;

import java.util.List;

import com.example.quiz_1140818.constants.ConstantsMessage;

import jakarta.validation.constraints.NotEmpty;

public class DeleteReq {

	@NotEmpty(message = ConstantsMessage.QUIZ_ID_LIST_IS_EMPTY)
	private List<Integer> quizIdList;

	public List<Integer> getQuizIdList() {
		return quizIdList;
	}

	public void setQuizIdList(List<Integer> quizIdList) {
		this.quizIdList = quizIdList;
	}

	
	
	
}
