package com.robotMES.comm;
import com.robotMES.admin.AdminController;
import com.robotMES.user.UserController;

public class ControllerFactory {
	public static CommControllerInterface make(String business) {
		CommControllerInterface controller = null;
		switch(business) {
		case "Admin" ->{controller = new AdminController();}
		case "User"  ->{controller = new UserController();}
		}
		
		return controller;
	}
}
