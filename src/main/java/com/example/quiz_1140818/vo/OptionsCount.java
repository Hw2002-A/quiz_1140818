package com.example.quiz_1140818.vo;

public class OptionsCount {

	private int code;

	private String optionName;

	private int count = 1;// 給定一個參數 預設值1

	
	public OptionsCount(int code, String optionName, int count) {
		super();
		this.code = code;
		this.optionName = optionName;
		this.count = count;
	}

	public OptionsCount() {
		super();

	}

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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
