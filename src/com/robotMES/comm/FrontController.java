package com.robotMES.comm;

import java.util.Scanner;

public class FrontController {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		CommControllerInterface controller = null;
		boolean isStop = false;
		
		do {
			System.out.println("""
					########################################
					   MES - Manufacturing Execution System
					########################################
					""");
			System.out.print("■ Login ID >> ");
			String job = sc.next();
			
			System.out.print("■ Login PW >> ");
			job += sc.next();
			
			switch(job) {
				case "Admin1234" -> {controller = ControllerFactory.make("Admin");}
				case "User1234" -> {controller = ControllerFactory.make("User");}
				case "end" -> {isStop = true; continue;}
				default -> {loginDisplay(); continue;}
			}
			controller.execute();
			
		}while(!isStop);
		
		sc.close();
		System.out.println("Program End....");
	}

	private static void loginDisplay() {
		System.out.println("""
				▶ 아이디 또는 패스워드가 일치하지 않습니다 
				
				""");
	}
	
	
	
}
