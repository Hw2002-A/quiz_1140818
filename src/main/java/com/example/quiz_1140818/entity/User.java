package com.example.quiz_1140818.entity;

import com.example.quiz_1140818.constants.ConstantsMessage;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class User {

	@NotBlank(message = ConstantsMessage.USER_NAME_ERROR)
	private String name;

	private String phone;

	// pk
	@NotBlank(message = ConstantsMessage.USER_EMAIL_ERROR)
	private String email;

	@Min(value = 1, message = ConstantsMessage.USER_AGE_ERRoR)
	private int age;

	private String gender;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(@NotBlank(message = "User name error!!") String name, String phone,
			@NotBlank(message = "User email error!!") String email,
			@Min(value = 1, message = "User age error!!") int age, String gender) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.age = age;
		this.gender = gender;
	}

	
}
