package com.robotMES.robotControll;

import java.util.List;
import java.util.Scanner;

public class RobotControllController {
	static Scanner sc = new Scanner(System.in);
	RobotControllService robotControllService = new RobotControllService();

	public void f_select() {
		List<RobotControllDTO> list = robotControllService.selectAll();
		RobotControllView.display(list);
	}
	
}
