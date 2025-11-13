package com.example.quiz_1140818;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Quiz1140818ApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	public void mapTest1() {
		// 選項編號,     選項,    次數
		Map<Integer, Map<String, Integer>> codeOpCountMap = new HashMap<>();
		Map<String, Integer> opCountMap = new HashMap<>();
		opCountMap.put("紅茶", 1);
		opCountMap.put("綠茶", 1);
		codeOpCountMap.put(1, opCountMap);
		System.out.println(codeOpCountMap);
		//==========================
		// 綠茶的次數 + 1
		opCountMap = codeOpCountMap.get(1);
		int count = opCountMap.get("綠茶") + 1;
		// 更新次數的值
		opCountMap.put("綠茶", count);
		System.out.println(opCountMap);
		System.out.println(codeOpCountMap);
	}
}
