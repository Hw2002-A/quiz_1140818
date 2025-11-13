package com.example.quiz_1140818.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.quiz_1140818.constants.ResCodeMessage;
import com.example.quiz_1140818.dao.AccountDao;
import com.example.quiz_1140818.entity.Account;
import com.example.quiz_1140818.response.BasicRes;

@Service
public class AccountService {

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private AccountDao accountDao;

	public BasicRes addInfo(String account, String password) {
		try {
			// 若文件有說明要在新增資訊之前帳號
			int count = accountDao.selectAccountByAccount(account);
			// 因為是透過pk欄位account來查詢是否有存在值,所以只有0和1
			if (count == 1) {
				return new BasicRes(ResCodeMessage.ACCOUNT_EXIST.getCode(), ResCodeMessage.ACCOUNT_EXIST.getMessage());
			}
			//存進 DB 中的密碼要記得加密
			accountDao.addInfo(account, encoder.encode(password));
			return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
		} catch (Exception e) {
			// 若是id已存在,新增則會失敗
			// 發生 Exception 時, 可以有以下2種處理方式
			// 1.固定的回覆訊息,但真正錯誤原因無法顯示
//		return new BasicRes(ResCodeMessage.PARAM_ACCOUNT_ERROR.getCode(), ResCodeMessage.PARAM_ACCOUNT_ERROR.getMessage());
			// 2. 將 catch 到的例外(Exception)拋出(throw)，再由自定義的類別
			// GlobalExceptionHandler 寫入(回覆)真正的錯誤訊息
			throw e;
		}
	}

	public BasicRes login(String account, String password) {
		Account data = accountDao.selectByAccount(account);
		if(data == null) {
			return new BasicRes(ResCodeMessage.NOT_FOUND.getCode(), ResCodeMessage.NOT_FOUND.getMessage());
		}
		if(!encoder.matches(password, data.getPassword())) {
			return new BasicRes(ResCodeMessage.PARAM_PASSWORD_ERROR.getCode(), ResCodeMessage.PARAM_PASSWORD_ERROR.getMessage());
		}
		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage(), data.isAdmin());
	}
}
