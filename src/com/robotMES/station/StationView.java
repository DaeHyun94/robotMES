package com.robotMES.station;

import java.util.List;

public class StationView {

	//여러건 출력
	public static void display(List<StationDTO> stationlist) {
		if(stationlist == null || stationlist.size() == 0) {
			System.out.println("현재 작업대가 존재하지 않습니다.");
			return;
		}
		
		System.out.println("====== 작업대 정보 =======");
		stationlist.stream().forEach(a-> System.out.println(a));
	}

	public static void display(StationDTO stationDTO) {
		if(stationDTO == null) {
			System.out.println("잘못된 입력");
			return;
		}
		
		System.out.println("※ 추가 완료");
	}
	
	public static void display (int stationId) {
		if(stationId == 0) {
			System.out.println("삭제 할 station이 존재하지 않습니다.");
			return;
		}
		System.out.println("※ 삭제 완료");
	}

	public static void display (String message) {
		if(message == null) {
			System.out.println("잘못된 입력");
			return;
		}
		System.out.println(message);
	}
	
	public static void menuDisplay() {
		System.out.println("""
				
				
				
				▒▒▒▒▒▒▒▒▒▒Station Menu▒▒▒▒▒▒▒▒▒▒
				    1.Station 전체 조회          
				    2.Station 추가              
				    3.Station 수정              
				    4.Station 제거              
				    5.나가기         
				
				""");   
		System.out.print("※ 메뉴 선택 >>>> ");
				
		
	}
	
	
}
