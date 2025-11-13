package com.example.quiz_1140818.request;

import java.time.LocalDate;

public class SearchReq {

	private String title ;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean getPublish;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public boolean isGetPublish() {
		return getPublish;
	}
	public void setGetPublish(boolean getPublish) {
		this.getPublish = getPublish;
	}
	
	
}
