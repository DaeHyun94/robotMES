package com.robotMES.user;

import java.util.List;

import com.robotMES.admin.AdminDTO;

public class UserView {

	public static void menuDisplay() {
			System.out.println("""
				
				
				
				▒▒▒▒▒▒▒▒▒▒유저 Menu▒▒▒▒▒▒▒▒▒▒
				    1.유저 전체조회          
				    2.유저 추가              
				    3.유저 수정             
				    4.유저 제거              
				    5.나가기         
				
				""");   
		System.out.print("※ 메뉴 선택 >>>> ");
	}

	public static void display(List<UserDTO> list) {
		if(list == null || list.size() == 0) {
			System.out.println("현재 유저가 존재하지 않습니다.");
			System.out.println("유저를 추가해주세요.");
			return;
		}
		
		System.out.println("=========== 유저정보 ==============");
		list.stream().forEach(a-> System.out.println(a));
		
	}

	public static void display(String message) {
		if(message == null) {
			System.out.println("잘못된 입력");
			return;
		}
		System.out.println(message);
		
	}
	
	public static void display(UserDTO userDTO) {
		if(userDTO == null) {
			System.out.println("잘못된 입력");
			return;
		}
		
		System.out.println("※ 추가 완료");
	}
	
	public static void display (int Id) {
		if(Id == 0) {
			System.out.println("삭제 할 관리자ID가 존재하지 않습니다.");
			return;
		}
		System.out.println("※ 삭제 완료");
	}
}
