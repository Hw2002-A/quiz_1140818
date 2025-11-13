package com.example.quiz_1140818.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.quiz_1140818.entity.Quiz;

import jakarta.transaction.Transactional;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

	@Modifying
	@Transactional
	@Query(value = "insert into quiz (title, description, start_date, end_date,"//
			+ " is_publish) values (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
	public void create(String title, String description, LocalDate startdate, LocalDate enddate, boolean is_publish);

	// 找當前id欄位的最大值
	@Query(value = "select max(id) from quiz", nativeQuery = true)
	public int selectMaxId();

	// select count(id) 逝去搜尋欄位 id 出現的次數,因為id是pk,所以結果只會是0或1
	@Query(value = "select count(id) from quiz where id = ?1", nativeQuery = true)
	public int selectCountId(int id);

	@Modifying
	@Transactional
	@Query(value = "update quiz set title = ?2, description = ?3, start_date = ?4,"
			+ "end_date = ?5, is_publish = ?6 where id = ?1 ", nativeQuery = true)
	public int update(int id, String title, String description, LocalDate startdate, LocalDate enddate,
			boolean is_publish);

	@Query(value = "select * from quiz", nativeQuery = true)
	public List<Quiz> getAll();

	@Query(value = "select * from quiz where title like %?1% and start_date >= ?2 and end_date <= ?3", nativeQuery = true)
	public List<Quiz> getSearch(String title, LocalDate startDate, LocalDate endDate);

	@Query(value = "select * from quiz where "
			+ " title like %?1% and start_date >= ?2 and end_date <= ?3 and is_publish is true", nativeQuery = true)
	public List<Quiz> getPublishSearch(String title, LocalDate startDate, LocalDate endDate);

	@Query(value = "select * from quiz where is_publish is true", nativeQuery = true)
	public List<Quiz> getPublishAll();

	@Modifying
	@Transactional
	@Query(value = "delete from quiz where id in (?1)", nativeQuery = true)
	public void deleteByIdIn(List<Integer> quizIdList);

	// 只刪除1. 未發布 2. 已發布且已發布的
	@Modifying
	@Transactional
	@Query(value = "delete from quiz where "
			+ " (is_publish is false or is_publish is true) and now() > end_date )) "
			+ " id in (?1)", nativeQuery = true)
	public void deleteByIdIn(List<Integer> quizIdList, LocalDate now);

	
	@Query(value = "select * from quiz where id = ?1", nativeQuery = true)
	public Quiz getById(int id);
}
