package com.example.quiz_1140818.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.quiz_1140818.entity.Question;
import com.example.quiz_1140818.entity.QuestionId;

import jakarta.transaction.Transactional;

@Repository
public interface QuestionDao extends JpaRepository<Question, QuestionId>{
	
	
	@Modifying
	@Transactional
	@Query(value = "insert into question (quiz_id, question_id, name, options_str,"//
			+ " type, is_required) values (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
	public void create(int quizId, int questionId, String name, String optionsStr, String type, boolean required);
	
	
	@Modifying
	@Transactional
	@Query(value = "delete from question where quiz_id = ?1"//
			, nativeQuery = true)
	public void deleteByQuizId(int quizId);
	
	
	@Modifying
	@Transactional
	@Query(value = "delete from question where quiz_id in (?1)" , nativeQuery = true)
	public void deleteByQuizIdIn(List<Integer> quizIdList);
	
	@Query(value = "select * from question where quiz_id = ?1"//
			, nativeQuery = true)
	public List<Question> getByQuizId(int quizId);
}
