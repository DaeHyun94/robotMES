package com.robotMES.admin;

import java.util.Scanner;

import com.robotMES.comm.CommControllerInterface;
import com.robotMES.process.ProcessController;
import com.robotMES.product.ProductController;
import com.robotMES.robot.RobotCotroller;
import com.robotMES.station.StationController;
import com.rrobotMES.order.OrderController;

public class AdminController implements CommControllerInterface{

	static Scanner sc = new Scanner(System.in);
	AdminService adminService = new AdminService();
	
	@Override
	public void execute() {
		boolean isStop = false;
		do {
			AdminView.menuDisplay();
			int job = sc.nextInt();
			
			switch(job) {
				case 1->{f_managementByStation();}
				case 2->{f_managementByRobot();}
				case 3->{f_managementByProduct();}
				case 4->{f_managementByOrder();}
				case 5->{f_managementByProcess();}
				default ->{}
			}
			
		}while(!isStop);
		
	}

	private void f_managementByProcess() {
		ProcessController processController = new ProcessController();
		processController.execute();
		
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

}
