package com.robotMES.userWork;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.robotMES.admin.AdminService;
import com.robotMES.comm.CommControllerInterface;
import com.robotMES.robotControll.RobotControllController;
import com.rrobotMES.order.OrderDTO;
import com.rrobotMES.order.OrderService;
import com.rrobotMES.order.OrderView;

public class UserWorkController implements CommControllerInterface{
	static Scanner sc = new Scanner(System.in);
	AdminService adminService = new AdminService();
	SimpleDateFormat sdf = new SimpleDateFormat("MMyyyyHHssddmm");
	
	@Override
	public void execute() {
		boolean isStop = false;
		do {
			UserWorkView.menuDisplay();
			int job = sc.nextInt();
			
			switch(job) {
			case 1->{f_choiceOrder();}
			case 2->{f_robotControl();}
			case 0->{isStop = true; continue; }
			default ->{continue;}
			}
			
			
			
			
		}while(!isStop);
		
	}

	private void f_robotControl() {
		boolean isStop = false;
		do {
			UserWorkView.robotDisplay();
			int job = sc.nextInt();
			
			switch(job) {
			case 1->{f_robotNow();}
			case 2->{f_robotControl();}
			case 3->{f_robotStop();}
			case 0->{isStop = true; continue; }
			default ->{continue;}
			}
			
			
			
			
		}while(!isStop);
	}

	private void f_robotStop() {
		
		
	}

	private void f_robotNow() {
		RobotControllController rcc = new RobotControllController();
		rcc.f_select();
		
	}

	private void f_choiceOrder() {
		OrderService orderService = new OrderService();
		List<OrderDTO> orderlist = orderService.selectAll();
		OrderView.display(orderlist);
		
		if(orderlist == null || orderlist.size() == 0) {
			System.out.println("오더 추가해주세요.");
			return;
		}
		
		OrderDTO order = null;
		String orderID = null;
		while(true) { //Product 체크
			System.out.print("참여할 오더ID 입력 :");
			orderID = sc.next();
			if(orderID.equals("0")) return;
			for(OrderDTO orderDTO : orderlist) {
				if(orderDTO.getId().equals(orderID)) {
					order = orderDTO;
					break;
				}
			}
			if(order == null) {
				System.out.println("없는 오더ID 입니다. 오더ID 다시입력하세요.[나가기 '0' 입력]");
			}else break;
		}
		 order.setUser_id("User");
		 orderService.update(order);
		 OrderView.display("▶ 작업참여가 완료 되었습니다.");
	}
}
