package com.rrobotMES.order;

import java.util.List;

public class OrderView {

	public static void menuDisplay() {
System.out.println("""
				
				
				
				▒▒▒▒▒▒▒▒▒▒오더 Menu▒▒▒▒▒▒▒▒▒▒
				    1.오더 전체 조회          
				    2.오더 추가              
				    3.오더 수정              
				    4.오더 제거              
				    5.나가기         
				
				""");   
		System.out.print("※ 메뉴 선택 >>>> ");
		
	}

	public static void display(List<OrderDTO> orderlist) {
		if(orderlist == null || orderlist.size() == 0) {
			System.out.println("현재 오더가 존재하지 않습니다.");
			return;
		}
		
		System.out.println("====== 오더 정보 =======");
		orderlist.stream().forEach(a-> System.out.println(a));
		
	}

	

}
