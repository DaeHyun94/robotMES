package com.robotMES.comm;

public class ControllerFactory {
	public static CommControllerInterface make(String business) {
		CommControllerInterface controller = null;
		switch(business) {
		case "Admin" ->{controller = new FrontAdmin();}
		case "User"  ->{controller = new FrontUser();}
		}
		
		return controller;
	}
}
