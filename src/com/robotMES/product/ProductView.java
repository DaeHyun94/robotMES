package com.robotMES.product;

import java.util.List;

public class ProductView {

	public static void menuDisplay() {
			System.out.println("""
				
				
				
				▒▒▒▒▒▒▒▒▒▒제품 Menu▒▒▒▒▒▒▒▒▒▒
				    1.제품 전체 조회          
				    2.제품 추가              
				    3.제품 수정              
				    4.제품 제거              
				    5.나가기         
				
				""");   
		System.out.print("※ 메뉴 선택 >>>> ");
		
	}

	public static void display(List<ProductDTO> productlist) {
		
		if(productlist == null || productlist.size() == 0) {
			System.out.println("현재 제품이 존재하지 않습니다.");
			System.out.println("제품을 추가해주세요.");
			return;
		}
		
		System.out.println("=========== 제품정보 ==============");
		productlist.stream().forEach(a-> System.out.println(a));
	}

	public static void display(ProductDTO productDTO) {
		if(productDTO == null) {
			System.out.println("잘못된 입력");
			return;
		}
		
		System.out.println("※ 추가 완료");
	}
	
	public static void display (String message) {
		if(message == null) {
			System.out.println("잘못된 입력");
			return;
		}
		System.out.println(message);
	}
	
	public static void display (int Id) {
		if(Id == 0) {
			System.out.println("삭제 할 제품이 존재하지 않습니다.");
			return;
		}
		System.out.println("※ 삭제 완료");
	}
}
