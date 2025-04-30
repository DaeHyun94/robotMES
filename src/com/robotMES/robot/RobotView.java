package com.robotMES.robot;

import java.util.List;

public class RobotView {
	//여러건 출력
		public static void display(List<RobotDTO> robotlist) {
			if(robotlist == null || robotlist.size() == 0) {
				System.out.println("현재 robot이 존재하지 않습니다.");
				return;
			}
			
			System.out.println("====== Station 정보 =======");
			robotlist.stream().forEach(robot-> System.out.println(robot));
		}

		public static void display(RobotDTO robotDTO) {
			if(robotDTO == null) {
				System.out.println("잘못된 입력");
				return;
			}
			
			System.out.println("※ 추가 완료");
		}
		
		public static void display (int robotId) {
			if(robotId == 0) {
				System.out.println("삭제 할 robot이 존재하지 않습니다.");
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
					
					
					
					▒▒▒▒▒▒▒▒▒▒robot Menu▒▒▒▒▒▒▒▒▒▒
					    1.robot 전체 조회          
					    2.robot 추가              
					    3.robot 수정              
					    4.robot 제거              
					    5.나가기         
					
					""");   
			System.out.print("※ 메뉴 선택 >>>> ");
					
			
		}
}
