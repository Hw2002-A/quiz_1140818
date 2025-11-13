package com.example.quiz_1140818.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.quiz_1140818.constants.QuestionType;
import com.example.quiz_1140818.constants.ResCodeMessage;
import com.example.quiz_1140818.dao.QuestionDao;
import com.example.quiz_1140818.dao.QuizDao;
import com.example.quiz_1140818.entity.Question;
import com.example.quiz_1140818.entity.Quiz;
import com.example.quiz_1140818.response.BasicRes;
import com.example.quiz_1140818.response.QuestionListRes;
import com.example.quiz_1140818.response.QuizListRes;
import com.example.quiz_1140818.vo.Options;
import com.example.quiz_1140818.vo.QuestionVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QuizService {

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private QuizDao quizDao;

	@Autowired
	private QuestionDao questionDao;

	/**
	 * @Transactional: <br>
	 *                 1. 使用於修改資料時(insert/update/delete)且有以下兩種情況<br>
	 *                 1.1 同一個方法中有使用多個Dao時,例如 create 中有同時使用 quizDao 和
	 *                 questionDao<br>
	 *                 1.2 同一個Dao 有修改多筆資料,例如下面方法中有使用 questionDao新增多筆問題<br>
	 *                 2. 其預設的有效作用是當程式發生
	 *                 RuntimeException(以及其子類別)時才會讓資料回朔,所以為在發生其他Exception時也可以讓資料回朔,<br>
	 *                 就要把作用範圍提升到所有例外的父類別:Exception <br>
	 *                 3. 要讓 @Transactional 有效的另一個條件必須要把發生的 Exception 給它 throw 出去
	 */

	@Transactional(rollbackFor = Exception.class)
	public BasicRes create(Quiz quiz, List<QuestionVo> questionVoList) throws Exception {

		try {
			// 檢查question
			BasicRes checkRes = checkQuestion(questionVoList);
			if (checkRes != null) {
				return checkRes;
			}
			// 檢查開始時間: 開始時間不能比結束時間晚 或 結束時間不能比開始時間早
			checkRes = checkDate(quiz.getStartDate(), quiz.getEndDate());
			if (checkRes != null) {
				return checkRes;
			}
			// 新增問卷quiz
			quizDao.create(quiz.getTitle(), quiz.getDescription(), quiz.getStartDate(), quiz.getEndDate(),
					quiz.isPublish());

			// 取 quiz_id , 因為quiz_id是流水號,要等資料進DB 後才會自動產生
			int quizId = quizDao.selectMaxId();
			// 處理Question
			// 把Vo轉成 Question entity 後, 再將資料寫進DB
			for (QuestionVo vo : questionVoList) {
				List<Options> optionsVoList = vo.getOptionsList();
				if (optionsVoList == null) {
					optionsVoList = new ArrayList<>();
				}
				// 將 QuestionVo 中的List<Options> 轉成字串
				String optionStr = mapper.writeValueAsString(vo.getOptionsList());
				// 新增Question
				// 確保這些問題都是同一個quizId --> 使用quiz中取得id
				questionDao.create(quizId, vo.getQuestionId(), vo.getName(), optionStr, vo.getType(), vo.isRequired());
			}
		} catch (Exception e) {
			throw e;
		}
		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
	}

	// 檢查question
	// 檢查type
	private BasicRes checkQuestion(List<QuestionVo> questionVoList) {
		for (QuestionVo item : questionVoList) {
			if (!QuestionType.checkAllType(item.getType())) {
				// 檢查的結果 是false表示 question 中的type不是設定的3種型態之一
				return new BasicRes(ResCodeMessage.QUESTION_TYPE_ERROR.getCode(),
						ResCodeMessage.QUESTION_TYPE_ERROR.getMessage());
			}
			// 檢查選項:
			// 正確的應該是:type是1.簡答題時,不會有選項;或2.選擇題(單、多選題)時要有選項
			// 以下是排除: 當type 是簡答題時確有選項
			if (!QuestionType.checkChoiceType(item.getType())) {
				// 選項是簡答題 且 選項List不是空
				if (!item.getOptionsList().isEmpty()) {
					return new BasicRes(ResCodeMessage.QUESTION_TYPE_OPTIONS_MISMATCH.getCode(),
							ResCodeMessage.QUESTION_TYPE_OPTIONS_MISMATCH.getMessage());
				}
			}
			if (QuestionType.checkChoiceType(item.getType())) {
				if (item.getOptionsList().isEmpty()) {
					return new BasicRes(ResCodeMessage.QUESTION_TYPE_OPTIONS_MISMATCH.getCode(),
							ResCodeMessage.QUESTION_TYPE_OPTIONS_MISMATCH.getMessage());
				}
			}
		}
		return null;
	}

	/**
	 * 檢查問卷日期 <br>
	 * 1. 開始日期不能比結束日期晚 或 結束日期不能比開始日期早<br>
	 * 2. 條件1 成立下 開始日期 不能比當前日期早<br>
	 * 3. startDate.isAfter(endDate):<br>
	 * 3.1 startDate 早於 endDate --> false<br>
	 * 3.2 startDate 等於 endDate --> false<br>
	 * 3.3 startDate 晚於 endDate --> true<br>
	 */
//	private BasicRes checkDate(LocalDate startDate, LocalDate endDate) {
//		// 正常來說，問卷的開始日期一定是等於或晚於結束日期，若 startDate.isAfter(endDate)
//		// 的結果是 true，則表示問卷的兩個時間是錯的
//		if (startDate.isAfter(endDate)) {
//			return new BasicRes(ResCodeMessage.QUIZ_DATE_ERROR.getCode(), ResCodeMessage.QUIZ_DATE_ERROR.getMessage());
//		}
//		// isBefore 也是不包含兩個日期相等
//		if (LocalDate.now().isBefore(startDate)) {
//			return new BasicRes(ResCodeMessage.QUIZ_DATE_ERROR.getCode(), ResCodeMessage.QUIZ_DATE_ERROR.getMessage());
//		}
//		return null;
//	};
	private BasicRes checkDate(LocalDate startDate, LocalDate endDate) {
		// 開始日期不能晚於結束日期
		if (startDate.isAfter(endDate)) {
			return new BasicRes(ResCodeMessage.QUIZ_DATE_ERROR.getCode(), "問卷開始日期不能晚於結束日期");
		}

		// 開始日期不能早於今天（如果需求是問卷只能今天開始或以後）
		if (startDate.isBefore(LocalDate.now())) {
			return new BasicRes(ResCodeMessage.QUIZ_DATE_ERROR.getCode(), "問卷開始日期不能早於今天");
		}

		// 日期正確
		return null;
	}

	@Transactional
	public BasicRes update(Quiz quiz, List<QuestionVo> questionVoList) throws Exception {
		try {
			// 檢查 quizId 是否存在於 DB
			int quizId = quiz.getId();
			// select count(id) 是去搜尋欄位 id 出現的次數,因為id是PK,所以結果只會是0或1
			if (quizDao.selectCountId(quizId) == 0) {// 0 表示quiz不存在
				return new BasicRes(ResCodeMessage.NOT_FOUND.getCode(), //
						ResCodeMessage.NOT_FOUND.getMessage());
			}
			// 檢查開始時間: 開始時間不能比結束時間晚 或 結束時間不能比開始時間早
			BasicRes checkRes = checkDate(quiz.getStartDate(), quiz.getEndDate());
			if (checkRes != null) {
				return checkRes;
			}
			// 更新quiz
			quizDao.update(quizId, //
					quiz.getTitle(), //
					quiz.getDescription(), //
					quiz.getStartDate(), //
					quiz.getEndDate(), //
					quiz.isPublish());
			// 更新question
			// 1. 刪除相同的 quizId 的所有問題
			questionDao.deleteByQuizId(quizId);
			// 2. 新增問題: 確認這些問題都是同一個 quizId
			// 把 Vo 轉成 Question entity 後, 再將資料寫進DB
			for (QuestionVo vo : questionVoList) {
				List<Options> optionsVoList = vo.getOptionsList();
				if (optionsVoList == null) {
					optionsVoList = new ArrayList<>();
				}
				// 將 QuestionVo 中的List<Options> 轉成字串
				String optionStr = mapper.writeValueAsString(vo.getOptionsList());
				// 新增Question
				// 確保這些問題都是同一個quizId --> 使用quiz中取得id
				questionDao.create(quizId, vo.getQuestionId(), vo.getName(), optionStr, vo.getType(), vo.isRequired());
			}
			return new BasicRes(ResCodeMessage.SUCCESS.getCode(), //
					ResCodeMessage.SUCCESS.getMessage());

		} catch (Exception e) {
			throw e;
		}
	}

	public QuizListRes getQuizList(boolean getPublish) {
		if (getPublish) {// getPublish 等同於 getPublish == true
			return new QuizListRes(ResCodeMessage.SUCCESS.getCode(), //
					ResCodeMessage.SUCCESS.getMessage(), quizDao.getPublishAll());

		}
		return new QuizListRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), quizDao.getAll());

	}

	public QuizListRes getQuizList(String title, LocalDate startDate, LocalDate endDate, boolean getPublish) {
		// 若 title 沒帶值(預設是 null) 或空字串 或 全空白字串 --> 一律轉成空字串
		// 到時 SQL 中搭配 like %空字串的title% 就把 所有title的 資料拉出來
		if (!StringUtils.hasText(title)) {
			title = "";
		}
		// 轉換沒有帶值得開始日期: 將開始日期改成很早之前的一個日期
		if (startDate == null) {
			startDate = LocalDate.of(1972, 1, 1);
		}
		// 轉換沒有帶值得結束日期: 將開始日期改成很久之後的一個日期
		if (endDate == null) {
			endDate = LocalDate.of(2999, 12, 31);
		}
		if(getPublish) {
			return new QuizListRes(ResCodeMessage.SUCCESS.getCode(), //
					ResCodeMessage.SUCCESS.getMessage(), quizDao.getPublishSearch(title, startDate, endDate));
		}
		return new QuizListRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), quizDao.getSearch(title, startDate, endDate));
	}

	public QuestionListRes getQuestionList(int quizId) throws Exception {
		// 檢查參數
		if (quizId <= 0) {
			return new QuestionListRes(ResCodeMessage.QUIZ_ID_ERROR.getCode(), //
					ResCodeMessage.QUIZ_ID_ERROR.getMessage());
		}
		// 檢查quizId是否存在於DB
		// select count(id) 逝去搜尋欄位 id 出現的次數,因為id是PK,所以結果只會是0或1
		if (quizDao.selectCountId(quizId) == 0) {// 0 表示quiz不存在
			return new QuestionListRes(ResCodeMessage.NOT_FOUND.getCode(), //
					ResCodeMessage.NOT_FOUND.getMessage());
		}
		List<Question> questionList = questionDao.getByQuizId(quizId);
		List<QuestionVo> voList = new ArrayList<>();
		// 將每個optionStr轉成List<Option>
		for (Question item : questionList) {
			try {
				List<Options> optionsList = mapper.readValue(item.getOptionsStr(), new TypeReference<>() {
				});
				// 把每個Question 的屬性值 塞到 QuestionVo 中
				QuestionVo vo = new QuestionVo(//
						item.getQuizId(), //
						item.getQuestionId(), //
						item.getName(), //
						optionsList, //
						item.getType(), //
						item.isRequired());
				// 將vo 放到voList中
				voList.add(vo);
			} catch (Exception e) {
				throw e;
			}

		}
		return new QuestionListRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), voList);
	}

	@Transactional(rollbackFor = Exception.class)
	public BasicRes deleteByQuizId(List<Integer> quizIdList) throws Exception {
		// 刪 quiz
		try {
			quizDao.deleteByIdIn(quizIdList);
			questionDao.deleteByQuizIdIn(quizIdList);
		} catch (Exception e) {
			throw e;
		}

		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage());
	}
}
