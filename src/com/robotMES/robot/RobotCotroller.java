package com.robotMES.robot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.robotMES.comm.CommControllerInterface;
import com.robotMES.station.StationView;

public class RobotCotroller implements CommControllerInterface{

	static Scanner sc = new Scanner(System.in);
	RobotService robotService = new RobotService();
	SimpleDateFormat sdf = new SimpleDateFormat("MMyyyyHHssddmm");
	
	@Override
	public void execute() {
		boolean isStop = false;
		do {
			RobotView.menuDisplay();
			int job = sc.nextInt();
			
			switch(job) {
			case 1->{f_selectAll();}
			case 2->{f_insert();}
			case 3->{f_update();}
			case 4->{f_delete();}
			case 5->{isStop = true; continue; }
			default ->{continue;}
			}
		}while(!isStop);
		
	}

	private void f_selectAll() {
		List<RobotDTO> robotlist = robotService.selectAll();
		RobotView.display(robotlist);
	}

	

	private void f_insert() {
		System.out.print("robot 이름 입력 :");
		String robotName = sc.next();
		while(true) {
			if(robotService.selectByName(robotName) != null) {
				System.out.println("이미 존재하는 이름입니다. 다른이름을 입력하세요.");
				System.out.print("robot 이름 입력 :");
				robotName = sc.next();
			}else {break;}
		}
		
		RobotDTO robot = RobotDTO.builder()
		.id(sdf.format(Calendar.getInstance().getTime()))
		.robot_name(robotName)
		.build();
		
		robotService.insert(robot);
		RobotView.display(robot);
	}
	
	private void f_update() {
		System.out.print("수정할 로봇 이름>>");
		String robotName = sc.next();
		RobotDTO exist_robot = robotService.selectByName(robotName);
		if (exist_robot == null) {
			StationView.display("존재하지않는 로봇 입니다.");
			return;
		}
		StationView.display("▶ 수정 작업을 실행합니다.");
		
		robotService.update(makeRobot(exist_robot.getId()));
		
		StationView.display("▶ 수정이 완료 되었습니다.");
		
	}
	
	private void f_delete() {
		System.out.print("제거할 robot 이름 입력 :");
		String robotName = sc.next();
		
		
		int result = robotService.delete(robotName);
		
		StationView.display(result);
		
	}
	
	private RobotDTO makeRobot(String robot_id) {
		System.out.print("-- 새로운 로봇 이름 >>");
		String robotName_new = sc.next();
		while(true) {
			if(robotService.selectByName(robotName_new) != null) {
				System.out.println("이미 존재하는 이름입니다. 다른이름을 입력하세요.");
				System.out.print("-- 새로운 로봇 이름 >>");
				robotName_new = sc.next();
			}else {break;}
		}	
		
		if(robotName_new.equals("0")) robotName_new = null;
		
		
		RobotDTO robot= RobotDTO.builder()
				.id(robot_id)
				.robot_name(robotName_new)
				.build();
		
		return robot;
	}

	

}
