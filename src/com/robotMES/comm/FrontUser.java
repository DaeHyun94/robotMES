package com.robotMES.comm;

import java.util.Scanner;

import com.robotMES.userWork.UserWorkController;

public class FrontUser implements CommControllerInterface{
	static Scanner sc = new Scanner(System.in);
	@Override
	public void execute() {
		UserWorkController userworkController = new UserWorkController();
		userworkController.execute();
	}

}
