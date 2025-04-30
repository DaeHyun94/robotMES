package com.robotMES.userWork;

public class UserWorkView {

	public static void menuDisplay() {
		// TODO Auto-generated method stub
		
	}

	public static void display(String message) {
		if(message == null) {
			System.out.println("잘못된 입력");
			return;
		}
		System.out.println(message);
		
	}
	
}
