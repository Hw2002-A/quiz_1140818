package com.example.quiz_1140818.constants;

public enum QuestionType {

	SINGEL("S"), MULTI("M"), TEXT("T");

	private String type;

	private QuestionType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static boolean checkAllType(String inputType) {
		//values(): 會取得 eumn 中的所有 type (SINGLE, MULTI, TEXT)
		for (QuestionType item : values()) {
			if (inputType.equalsIgnoreCase(item.getType())) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkChoiceType(String inputType) {
		if (inputType.equalsIgnoreCase(QuestionType.SINGEL.getType())
				|| inputType.equalsIgnoreCase(QuestionType.MULTI.getType())) {
			return true;
		}
		return false;
	}

	public static boolean checkTextType(String inputType) {
		if(inputType.equalsIgnoreCase(QuestionType.TEXT.getType())) {
			return true;
		}
		
		return false;
	}
}
