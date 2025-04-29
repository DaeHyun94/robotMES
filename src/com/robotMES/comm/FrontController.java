package com.robotMES.comm;

import java.util.Scanner;

public class FrontController {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		CommControllerInterface controller = null;
		boolean isStop = false;
		
		do {
			System.out.println("#MES - Manufacturing Execution System");
			System.out.print("#Input >> ");
			String job = sc.next();
			
			switch(job) {
				case "Admin" -> {controller = ControllerFactory.make("Admin");}
				case "end" -> {isStop = true; continue;}
				default -> {continue;}
			}
			controller.execute();
			
		}while(!isStop);
		
		sc.close();
		System.out.println("Program End....");
	}
}
