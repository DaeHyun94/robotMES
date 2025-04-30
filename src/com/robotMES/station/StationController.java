package com.robotMES.station;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.robotMES.comm.CommControllerInterface;

public class StationController implements CommControllerInterface{

	static Scanner sc = new Scanner(System.in);
	StationService stationService = new StationService();
	SimpleDateFormat sdf = new SimpleDateFormat("MMyyyyHHssddmm");
	
	@Override
	public void execute() {
		boolean isStop = false;
		do {
			StationView.menuDisplay();
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
		List<StationDTO> stationlist = stationService.selectAll();
		StationView.display(stationlist);
	}

	

	private void f_insert() {
		
		System.out.print("Station 이름 입력 :");
		String stationName = sc.next();
		while(true) {
			if(stationService.selectByName(stationName) != null) {
				System.out.println("이미 존재하는 이름입니다. 다른이름을 입력하세요.");
				System.out.print("Station 이름 입력 :");
				stationName = sc.next();
			}else {break;}
		}	
		
		
		System.out.print("Station 타입 입력 :");
		String stationType = sc.next();
		
		StationDTO station = StationDTO.builder()
		.id(sdf.format(Calendar.getInstance().getTime()))
		.station_name(stationName)
		.station_type(stationType)
		.build();
		
		stationService.insert(station);
		StationView.display(station);
	}
	
	private void f_update() {
		System.out.print("수정할 작업대 이름>>");
		String stationName = sc.next();
		StationDTO exist_station = stationService.selectByName(stationName);
		if (exist_station == null) {
			StationView.display("존재하지않는 작업대 입니다.");
			return;
		}
		StationView.display("▶ 수정 작업을 실행합니다.");
		stationService.update(makeStation(exist_station.getId()));
		StationView.display("▶ 수정이 완료 되었습니다.");
		
	}
	
	private void f_delete() {
		System.out.print("제거할 Station 이름 입력 :");
		String stationName = sc.next();
		
		
		int result = stationService.delete(stationName);
		
		StationView.display(result);
		
	}
	
	private StationDTO makeStation(String stationID) {
		System.out.print("-- 새로운 작업대 이름 >>");
		String stationName_new = sc.next();
		while(true) {
			if(stationService.selectByName(stationName_new) != null) {
				System.out.println("이미 존재하는 이름입니다. 다른이름을 입력하세요.");
				System.out.print("-- 새로운 작업대 이름 >>");
				stationName_new = sc.next();
			}else {break;}
		}	
			
		System.out.print("-- 새로운 작업대 타입 >>");
		String stationType_new = sc.next();
		
		if(stationName_new.equals("0")) stationName_new = null;
		if(stationType_new.equals("0")) stationType_new = null;
		
		StationDTO station= StationDTO.builder()
				.id(stationID)
				.station_name(stationName_new)
				.station_type(stationType_new)
				.build();
		
		return station;
	}
	
	
}
