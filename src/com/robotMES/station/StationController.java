package com.robotMES.station;

import java.util.List;
import java.util.Scanner;

import com.robotMES.comm.CommControllerInterface;

public class StationController implements CommControllerInterface{

	static Scanner sc = new Scanner(System.in);
	StationService StationService = new StationService();
	
	@Override
	public void execute() {
		boolean isStop = false;
		do {
			StationView.menuDisplay();
			int job = sc.nextInt();
			
			switch(job) {
			case 1->{f_selectAllStation();}
			case 2->{f_insertStation();}
			case 3->{f_updateStation();}
			case 4->{f_deleteStataion();}
			case 5->{isStop = true; continue; }
			default ->{continue;}
			}
			
			
			
			
		}while(!isStop);
		
	}

	private void f_selectAllStation() {
		List<StationDTO> stationlist = StationService.selectAllStation();
		StationView.display(stationlist);
	}

	

	private void f_insertStation() {
		System.out.print("Station 이름 입력 :");
		String stationName = sc.next();
		System.out.println("Station 타입 입력 :");
		String stationType = sc.next();
		
		StationDTO station = StationDTO.builder()
		.id("0003")
		.station_name(stationName)
		.station_type(stationType)
		.build();
		
		StationService.stationInsert(station);
		StationView.display(station);
	}
	
	private void f_updateStation() {
		// TODO Auto-generated method stub
		
	}
	
	private void f_deleteStataion() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
