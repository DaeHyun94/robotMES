package com.robotMES.comm;

import java.util.Scanner;

public class FrontUser implements CommControllerInterface{
	static Scanner sc = new Scanner(System.in);
	@Override
	public void execute() {
		boolean isStop = false;
		do {
			menuDisplay();
			int job = sc.nextInt();
			
			switch(job) {
				case 1->{}
				default ->{continue;}
			}
			
		}while(!isStop);
		
	}

	private void menuDisplay() {
		System.out.println("""
				=================================
				업무 목록 선택             0.로그아웃
				
				1.공정선택    2.로봇제어    3.공정신호   
				=================================
				""");
		System.out.print("(선택)>>>");
		
	}

}
