package com.robotMES.admin;

import java.util.Scanner;

import com.robotMES.comm.CommControllerInterface;
import com.robotMES.station.StationController;

public class AdminController implements CommControllerInterface{

	static Scanner sc = new Scanner(System.in);
	AdminService adminService = new AdminService();
	
	@Override
	public void execute() {
		boolean isWork = false;
		do {
			AdminView.menuDisplay();
			int job = sc.nextInt();
			
			switch(job) {
				case 1->{f_managementByStation();}
				case 2->{}
				default ->{}
			}
			
			
			
			
		}while(isWork);
		
	}

	private void f_managementByStation() {
		StationController stationController = new StationController();
		stationController.execute();
	}

}
