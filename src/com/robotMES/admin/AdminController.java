package com.robotMES.admin;

import java.util.Scanner;

import com.robotMES.comm.CommControllerInterface;

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
			case 1->{f_selectAll();}
			case 2->{f_insert();}
			case 3->{f_update();}
			case 4->{f_delete();}
			case 5->{isStop = true; continue; }
			default ->{continue;}
			}
			
			
			
			
		}while(!isStop);
		
	}

	private void f_delete() {
		// TODO Auto-generated method stub
		
	}

	private void f_update() {
		// TODO Auto-generated method stub
		
	}

	private void f_insert() {
		// TODO Auto-generated method stub
		
	}

	private void f_selectAll() {
		// TODO Auto-generated method stub
		
	}

}
