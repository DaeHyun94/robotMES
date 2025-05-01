package com.robotMES.robotControll;

import java.util.List;

public class RobotControllView {

	public static void display(List<RobotControllDTO> list) {
		if(list == null || list.size() == 0) {
			System.out.println("현재 로봇이 존재하지 않습니다.");
			return;
		}
		
		System.out.println("=========== 로봇현황 ==============");
		list.stream().forEach(a-> System.out.println(a));
		
	}

}
