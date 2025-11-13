package com.example.quiz_1140818.request;

import java.util.List;
import com.example.quiz_1140818.constants.ConstantsMessage;

import com.example.quiz_1140818.entity.Quiz;
import com.example.quiz_1140818.vo.QuestionVo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreateUpdateReq {

	// 嵌套驗證: 屬性是個自定義類別,且也有對該類別中的屬性加上限制
	// 因為有對類別Quiz中的屬性加上限制,為了要讓那些限制生效,就要在Quiz上面加上@Valid
	@Valid
	@NotNull(message = ConstantsMessage.OPTIONS_OPTIONS_NAME_ERROR)
	private Quiz quiz;

	@Valid
	@NotEmpty(message = ConstantsMessage.QUESTION_OPTIONS_ERROR)
	private List<QuestionVo> questionVoList;

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public List<QuestionVo> getQuestionVoList() {
		return questionVoList;
	}

	public void setQuestionVoList(List<QuestionVo> questionVoList) {
		this.questionVoList = questionVoList;
	}

	

	

}
