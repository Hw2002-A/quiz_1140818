package com.example.quiz_1140818.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_1140818.request.BasicReq;
import com.example.quiz_1140818.response.BasicRes;
import com.example.quiz_1140818.service.AccountService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping(value = "add_info")
	public BasicRes addInfo(@Valid @RequestBody BasicReq req) {
		return accountService.addInfo(req.getAccount(), req.getPassword());
	}
	
	@PostMapping(value = "quiz_login")
	public BasicRes login(@Valid @RequestBody BasicReq req) {
		return accountService.login(req.getAccount(), req.getPassword());
	}
	
	
}
