package com.robotMES.comm;
import com.robotMES.admin.AdminController;

public class ControllerFactory {
	public static CommControllerInterface make(String business) {
		CommControllerInterface controller = null;
		switch(business) {
		case "Admin" ->{controller = new AdminController();}
		}
		
		return controller;
	}
}
