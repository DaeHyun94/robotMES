package com.robotMES.userWork;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.robotMES.admin.AdminDTO;
import com.robotMES.admin.AdminService;
import com.robotMES.admin.AdminView;
import com.robotMES.comm.CommControllerInterface;
import com.robotMES.product.ProductDTO;
import com.robotMES.product.ProductView;
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
			case 5->{isStop = true; continue; }
			default ->{continue;}
			}
			
			
			
			
		}while(!isStop);
		
	}

	private void f_choiceOrder() {
System.out.println("dd");
		OrderService orderService = new OrderService();
		List<OrderDTO> orderlist = orderService.selectAll();
		OrderView.display(orderlist);
		
		if(orderlist == null || orderlist.size() == 0) {
			System.out.println("오더 추가해주세요.");
			return;
		}
		
		OrderDTO order = new OrderDTO(); 
		
		while(true) { //Product 체크
			System.out.print("참여할 오더ID 입력 :");
			String orderID = sc.next();
			if(orderID.equals("0")) return;
			
			for(OrderDTO ordertDTO : orderlist) {
				if(ordertDTO.getId().equals(orderID)) {
					order = ordertDTO;
					break;
				}
			}

			if(order == null) {
				System.out.println("없는 오더ID 입니다. 오더ID 다시입력하세요.[나가기 '0' 입력]");
			}else break;
		}
	}
}
