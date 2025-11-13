package com.example.quiz_1140818.response;

import com.example.quiz_1140818.vo.StatisticVo;

public class StatisticRes extends BasicRes {

	private StatisticVo statisticVo;

	public StatisticRes(int code, String message, boolean admin) {
		super(code, message, admin);
		
	}

	public StatisticRes(int code, String message) {
		super(code, message);
		
	}

	public StatisticRes(int code, String message, StatisticVo statisticVo) {
		super(code, message);
		this.statisticVo = statisticVo;
	}

	public StatisticVo getStatisticVo() {
		return statisticVo;
	}

	public void setStatisticVo(StatisticVo statisticVo) {
		this.statisticVo = statisticVo;
	}

}
