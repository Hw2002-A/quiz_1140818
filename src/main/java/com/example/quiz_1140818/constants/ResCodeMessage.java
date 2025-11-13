package com.example.quiz_1140818.constants;

public enum ResCodeMessage {

	SUCCESS(200, "Success!!"), //
	SUCCESS2(201, "Success!!"), //
	PARAM_COLOR_ERROR(400, "Param color error!!"), //
	ADD_INFO_FAILED(400, "add info failed!!"), //
	PARAM_ACCOUNT_ERROR(400, "Param account error!!"), //
	PARAM_PASSWORD_ERROR(400, "Param password error!!"), //
	PASSWORD_MISMATCH(400, "password mismatch!!"), //
	ACCOUNT_EXIST(400, "account exist"), //
	QUESTION_TYPE_ERROR(400, ConstantsMessage.QUESTION_TYPE_ERROR),//
	QUESTION_TYPE_OPTIONS_MISMATCH(400,"Question type and options mismatch!!" ),
	QUIZ_DATE_ERROR(400, "Quiz date error!!"),//
	QUIZ_ID_ERROR(400, "Quiz id error!!"),//
	RDIO_ANSWER_ERROR(400, "Quiz id error!!"),//
	RCHECKBOX_ANSWER_IS_REQUIRED(400, "checkbox answer error!!"),//
	RADIO_ANSWER_IS_REQUIRED(400, "radio answer error!!"),//
	TEXT_ANSWER_IS_REQUIRED(400, "text answer error!!"),//
	CHECKBOX_ANSWER_IS_REQUIRED(400, "checkbox answer error!!"),//
	QUESTION_OPTION_MISMATCH(400, "Question option mismatch!!"),//
	NOT_FOUND(404, "Not Found!!");

	private int code;

	private String message;

	private ResCodeMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
