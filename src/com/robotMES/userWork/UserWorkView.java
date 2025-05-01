package com.robotMES.userWork;

public class UserWorkView {

	public static void menuDisplay() {
		System.out.println("""
				=================================
				업무 목록 선택             0.로그아웃
				
				1.공정선택    2.로봇제어    3.공정신호   
				=================================
				""");
		System.out.print("(선택)>>>");
		
	}

	public static void display(String message) {
		if(message == null) {
			System.out.println("잘못된 입력");
			return;
		}
		System.out.println(message);
		
	}

	public static void robotDisplay() {
		System.out.println("""
				=================================
				                          0.나가기
				
				1.로봇현황    2.작업부여    3.긴급정지   
				=================================
				""");
		System.out.print("(선택)>>>");
		
	}
	
}
