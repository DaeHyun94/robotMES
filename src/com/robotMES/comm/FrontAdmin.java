package com.robotMES.comm;

import java.util.Scanner;

import com.robotMES.admin.AdminController;
import com.robotMES.product.ProductController;
import com.robotMES.robot.RobotCotroller;
import com.robotMES.station.StationController;
import com.robotMES.user.UserController;
import com.rrobotMES.order.OrderController;

public class FrontAdmin implements CommControllerInterface{

	static Scanner sc = new Scanner(System.in);
	
	@Override
	public void execute() {
		boolean isStop = false;
		do {
			menuDisplay();
			int job = sc.nextInt();
			
			switch(job) {
				case 1->{f_managementByStation();}
				case 2->{f_managementByRobot();}
				case 3->{f_managementByProduct();}
				case 4->{f_managementByOrder();}
				//case 5->{f_managementByAdmin();}
				//case 6->{f_managementByUser();}
				case 0->{logoutDisplay();; isStop = true; continue;}
				default ->{continue;}
			}
			
		}while(!isStop);
		
	}


	private void f_managementByUser() {
		UserController userController = new UserController();
		userController.execute();
		
	}


	private void f_managementByAdmin() {
		AdminController adminController = new AdminController();
		adminController.execute();
		
	}

	private void f_managementByOrder() {
		OrderController orderController = new OrderController();
		orderController.execute();
	}

	private void f_managementByProduct() {
		ProductController productController = new ProductController();
		productController.execute();
		
	}

	private void f_managementByRobot() {
		RobotCotroller robotCotroller = new RobotCotroller();
		robotCotroller.execute();
		
	}

	private void f_managementByStation() {
		StationController stationController = new StationController();
		stationController.execute();
	}

	private void menuDisplay() {
		System.out.println("""
				===================================
				업무 목록 선택             0.로그아웃
				
				1.공정    2.로봇    3.제품    4.오더 
				===================================
				""");
		System.out.print("(선택)>>>");
	}
	
	private void logoutDisplay() {
		System.out.println("""
				--------- 로그아웃되었습니다.
				
				
				""");
	}
}
