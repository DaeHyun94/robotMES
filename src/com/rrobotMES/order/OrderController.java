package com.rrobotMES.order;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.robotMES.comm.CommControllerInterface;
import com.robotMES.product.ProductDTO;
import com.robotMES.product.ProductView;
import com.robotMES.station.StationDTO;
import com.robotMES.station.StationView;

public class OrderController implements CommControllerInterface{

	static Scanner sc = new Scanner(System.in);
	OrderService orderService = new OrderService();
	SimpleDateFormat sdf = new SimpleDateFormat("MMyyyyHHssddmm");
	
	@Override
	public void execute() {
		boolean isStop = false;
		do {
			OrderView.menuDisplay();
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
		List<OrderDTO> orderlist = orderService.selectAll();
		OrderView.display(orderlist);
	}
	
	private void f_insert() {
		List<ProductDTO> productlist = orderService.selectProduct();
		ProductView.display(productlist);
		
		if(productlist == null || productlist.size() == 0) {
			System.out.println("제품을 추가해주세요.");
			return;
		}
		
		String productName = null;
		String from_station_name = null;
		String to_station_name = null;
		int goal_count = 0;
		
		
        ProductDTO product = null;
        StationDTO station_from = null;
        StationDTO station_to = null;
        
		while(true) { //Product 체크
			System.out.print("제품 이름 입력 :");
			productName = sc.next();
			if(productName.equals("0")) return;
			
			for(ProductDTO productDTO : productlist) {
				if(productDTO.getProduct_name().equals(productName)) {
					product = productDTO;
					break;
				}
			}
			if(product == null) {
				System.out.println("없는 제품이름 입니다. 제품이름을 다시입력하세요.[나가기 '0' 입력]");
			}else break;
		}
		
		int productGoal = product.getGoal_count();
		while(true) {
			System.out.print("목표 제품 수량 입력 :");
			goal_count = sc.nextInt();
			if(goal_count == 0) return;
			if(goal_count > productGoal) {
				System.out.println("수량을 넘었습니다. 목표 제품 수량을 다시입력하세요.[나가기 '0' 입력]");
			}else {break;}
		}
		
		List<StationDTO> stationlist = orderService.selectStation();
		StationView.display(stationlist);
		if(stationlist == null || stationlist.size() == 0) {
			System.out.println("공정을 추가해주세요.");
			return;
		}

		while(true) { //from Station 체크
			System.out.print("시작 공정 이름 입력 :");
			from_station_name = sc.next();
			if(from_station_name.equals("0")) return;
			
			for(StationDTO stationDTO : stationlist) {
				if(stationDTO.getStation_name().equals(from_station_name)) {
					station_from = stationDTO;
					break;
				}
			}
			if(station_from == null) {
				System.out.println("없는 공정이름 입니다. 공정이름을 다시입력하세요.[나가기 '0' 입력]");
			}else break;
		}
		
		
		while(true) { //to Station 체크
			System.out.print("종료 공정 이름 입력 :");
			to_station_name = sc.next();
			if(to_station_name.equals("0")) return;
			
			for(StationDTO stationDTO : stationlist) {
				if(stationDTO.getStation_name().equals(to_station_name)) {
					station_to = stationDTO;
					break;
				}
			}
			if(station_to == null) {
				System.out.println("없는 공정이름 입니다. 공정이름을 다시입력하세요.[나가기 '0' 입력]");
			}else break;
		}
	
		
		orderService.insert(product,station_from,station_to);
		
	}
	
	private void f_delete() {
		
		
	}

	private void f_update() {
		// TODO Auto-generated method stub
		
	}

	

	

}
