package com.robotMES.admin;

public class AdminView {
	public static void menuDisplay() {
		System.out.println("""
				===============Admin Menu==================
				1.Station 관리
				2.Product 관리
				3.Robot 관리
				4.Order 관리""");
		System.out.print("※ 메뉴 선택 >>>> ");
	}
}
