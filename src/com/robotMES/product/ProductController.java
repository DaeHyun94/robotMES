package com.robotMES.product;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.robotMES.comm.CommControllerInterface;
import com.robotMES.station.StationView;

public class ProductController implements CommControllerInterface{

	static Scanner sc = new Scanner(System.in);
	ProductService productService = new ProductService();
	SimpleDateFormat sdf = new SimpleDateFormat("MMyyyyHHssddmm");
	
	@Override
	public void execute() {
		boolean isStop = false;
		do {
			ProductView.menuDisplay();
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
		List<ProductDTO> productlist = productService.selectAll();
		ProductView.display(productlist);
	}

	

	private void f_insert() {
		
		System.out.print("제품 이름 입력 :");
		String productName = sc.next();
		while(true) {
			if(productService.selectByName(productName) != null) {
				System.out.println("이미 존재하는 이름입니다. 다른이름을 입력하세요.");
				System.out.print("제품 이름 입력 :");
				productName = sc.next();
			}else {break;}
		}	
		
		
		System.out.print("제품 타입 입력 :");
		String productType = sc.next();
		System.out.print("목표 수량 입력 :");
		int goalCount = sc.nextInt();
		
		ProductDTO product = ProductDTO.builder()
		.id(sdf.format(Calendar.getInstance().getTime()))
		.product_name(productName)
		.product_type(productType)
		.goal_count(goalCount)
		.build();
		
		productService.insert(product);
		ProductView.display(product);
	}
	
	private void f_update() {
		System.out.print("수정할 제품 이름>>");
		String productName = sc.next();
		ProductDTO exist = productService.selectByName(productName);
		if (exist == null) {
			StationView.display("존재하지않는 제품 입니다.");
			return;
		}
		ProductView.display("▶ 수정 작업을 실행합니다.");
		
		productService.update(makeProduct(exist.getId()));
		
		ProductView.display("▶ 수정이 완료 되었습니다.");
		
	}
	
	private void f_delete() {
		System.out.print("제거할 제품 이름 입력 :");
		String productName = sc.next();
		
		
		int result = productService.delete(productName);
		
		ProductView.display(result);
		
	}
	
	private ProductDTO makeProduct(String ID) {
		System.out.print("-- 새로운 제품 이름 >>");
		String productName_new = sc.next();
		while(true) {
			if(productService.selectByName(productName_new) != null) {
				System.out.println("이미 존재하는 이름입니다. 다른이름을 입력하세요.");
				System.out.print("-- 새로운 제품 이름 >>");
				productName_new = sc.next();
			}else {break;}
		}	
			
		System.out.print("-- 새로운 제품 타입 >>");
		String productType_new = sc.next();
		
		System.out.print("-- 새로운 제품 목표수량 >>");
		Integer goalCount_new = sc.nextInt();
		
		if(productName_new.equals("0")) productName_new = null;
		if(productType_new.equals("0")) productType_new = null;
		if(goalCount_new == 0) goalCount_new = null;
		
		ProductDTO station= ProductDTO.builder()
				.id(ID)
				.product_name(productName_new)
				.product_type(productType_new)
				.goal_count(goalCount_new)
				.build();
		
		return station;
	}
}
