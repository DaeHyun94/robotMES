package com.robotMES.admin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.robotMES.comm.CommControllerInterface;
import com.robotMES.product.ProductDTO;
import com.robotMES.product.ProductView;
import com.robotMES.station.StationView;

public class AdminController implements CommControllerInterface{

	static Scanner sc = new Scanner(System.in);
	AdminService adminService = new AdminService();
	SimpleDateFormat sdf = new SimpleDateFormat("MMyyyyHHssddmm");
	
	@Override
	public void execute() {
		boolean isStop = false;
		do {
			AdminView.menuDisplay();
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

	private void f_delete() {
		System.out.print("제거할 관리자ID 입력 :");
		String admin = sc.next();
		
		
		int result = adminService.delete(admin);
		
		AdminView.display(result);
		
	}

	private AdminDTO makeAdmin(String id) {
		System.out.print("-- 새로운 관리자 ID >>");
		String adminID_new = sc.next();
		while(true) {
			if(adminService.selectByName(adminID_new) != null) {
				System.out.println("이미 존재하는 ID입니다. 다른ID를 입력하세요.");
				System.out.print("-- 새로운 관리자 ID>>");
				adminID_new= sc.next();
			}else {break;}
		}	
			
		System.out.print("-- 새로운 관리자 PW >>");
		String adminPW_new = sc.next();
	
		
		if(adminID_new.equals("0")) adminID_new = null;
		if(adminPW_new.equals("0")) adminPW_new = null;

		
		AdminDTO admin= AdminDTO.builder()
				.id(id)
				.admin_id(adminID_new)
				.admin_password(adminPW_new)
				.build();
		
		return admin;
	}

	private void f_update() {
		System.out.print("수정할 관리자 ID>>");
		String admin_id = sc.next();
		AdminDTO exist = adminService.selectByName(admin_id);
		if (exist == null) {
			AdminView.display("존재하지않는 관리자 입니다.");
			return;
		}
		AdminView.display("▶ 수정 작업을 실행합니다.");
		
		adminService.update(makeAdmin(exist.getId()));
		
		AdminView.display("▶ 수정이 완료 되었습니다.");
		
	}

	private void f_insert() {
		System.out.print("관리자 ID 입력 :");
		String adminName = sc.next();
		while(true) {
			if(adminService.selectByName(adminName) != null) {
				System.out.println("이미 존재하는 아이디입니다. 다른아이디를 입력하세요.");
				System.out.print("아이디 입력 :");
				adminName = sc.next();
			}else {break;}
		}	
	
		System.out.print("비밀번호 입력 :");
		String adminPassword = sc.next();
		
		AdminDTO admin = AdminDTO.builder()
		.id(sdf.format(Calendar.getInstance().getTime()))
		.admin_id(adminName)
		.admin_password(adminPassword)
		.build();
		
		adminService.insert(admin);
		AdminView.display(admin);
		
	}

	private void f_selectAll() {
		List<AdminDTO> list = adminService.selectAll();
		AdminView.display(list);
		
	}

}
