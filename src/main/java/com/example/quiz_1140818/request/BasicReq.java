package com.example.quiz_1140818.request;

import com.example.quiz_1140818.constants.ConstantsMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class BasicReq {

	/**
	 * 1. @NotBlank(message = "Account format error!!")<br>
	 * 2. @NotBlank: 表示限制字串 account 不能是 null 或 空字串 或 全空白字串<br>
	 * 3. @Pattern: 可以使用正規表達式的限制<br>
	 * 4. message 後面的字串表示當違反 @NotBlank 的限制時會得到的提示訊息<br>
	 * 4.1 message 後面的字串值只能寫死(固定不變)，若要參數化，則必須要在變數前面加上 final<br>
	 * 4.2 其餘可看類別 ConstantsMessage 中的說明
	 */

	@NotBlank(message = ConstantsMessage.PARAM_ACCOUNT_ERROR)
	@Pattern(regexp = "\\w{3,8}", message = "Account 格式錯誤")
	private String account;

	@NotBlank(message = ConstantsMessage.PARAM_PASSWORD_ERROR)
	@Pattern(regexp = "\\w{8,16}", message = "Password 格式錯誤")
	private String password;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
