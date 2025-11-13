package com.example.quiz_1140818.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@Column(name = "account")
	private String account;

	@Column(name = "password")
	private String password;
	
	@Column(name = "is_admin")
	private boolean admin;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
