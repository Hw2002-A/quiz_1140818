package com.example.quiz_1140818.vo;

import com.example.quiz_1140818.constants.ConstantsMessage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Options {

	@Min(value = 1, message = ConstantsMessage.OPTIONS_CODE_ERROR)
	private int code;

	@NotBlank(message = ConstantsMessage.OPTIONS_OPTIONS_NAME_ERROR)
	private String optionName;

	//多選答案
	private boolean checkBoolean;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public boolean isCheckBoolean() {
		return checkBoolean;
	}

	public void setCheckBoolean(boolean checkBoolean) {
		this.checkBoolean = checkBoolean;
	}

}
