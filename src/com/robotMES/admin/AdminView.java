package com.robotMES.admin;

import java.util.List;

public class AdminView {
	public static void menuDisplay() {
			System.out.println("""
				
				
				
				▒▒▒▒▒▒▒▒▒▒관리자 Menu▒▒▒▒▒▒▒▒▒▒
				    1.관리자 전체조회          
				    2.관리자 추가              
				    3.관리자 수정             
				    4.관리자 제거              
				    5.나가기         
				
				""");   
		System.out.print("※ 메뉴 선택 >>>> ");
	}

	public static void display(List<AdminDTO> list) {
		if(list == null || list.size() == 0) {
			System.out.println("현재 관리자가 존재하지 않습니다.");
			System.out.println("관리자를 추가해주세요.");
			return;
		}
		
		System.out.println("=========== 관리자정보 ==============");
		list.stream().forEach(a-> System.out.println(a));
		
	}

	public static void display(String message) {
		if(message == null) {
			System.out.println("잘못된 입력");
			return;
		}
		System.out.println(message);
		
	}
	
	public static void display(AdminDTO adminDTO) {
		if(adminDTO == null) {
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
