package com.robotMES.user;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import com.robotMES.comm.CommControllerInterface;


public class UserController implements CommControllerInterface{

	static Scanner sc = new Scanner(System.in);
	UserService userService = new UserService();
	SimpleDateFormat sdf = new SimpleDateFormat("MMyyyyHHssddmm");
	
	@Override
	public void execute() {
		boolean isStop = false;
		do {
			UserView.menuDisplay();
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
		System.out.print("삭제할 유저ID 입력 :");
		String user = sc.next();
		
		
		int result = userService.delete(user);
		
		UserView.display(result);
		
	}

	private UserDTO makeUser(String id) {
		System.out.print("-- 새로운 유저 ID >>");
		String userID_new = sc.next();
		while(true) {
			if(userService.selectByName(userID_new) != null) {
				System.out.println("이미 존재하는 ID입니다. 다른ID를 입력하세요.");
				System.out.print("-- 새로운 유저 ID>>");
				userID_new= sc.next();
			}else {break;}
		}	
			
		System.out.print("-- 새로운 유저 PW >>");
		String userPW_new = sc.next();
	
		
		if(userID_new.equals("0")) userID_new = null;
		if(userPW_new.equals("0")) userPW_new = null;

		
		UserDTO user= UserDTO.builder()
				.id(id)
				.user_id(userID_new)
				.user_password(userPW_new)
				.build();
		
		return user;
	}

	private void f_update() {
		System.out.print("수정할 유저 ID>>");
		String user_id = sc.next();
		UserDTO exist = userService.selectByName(user_id);
		if (exist == null) {
			UserView.display("존재하지않는 유저 입니다.");
			return;
		}
		UserView.display("▶ 수정 작업을 실행합니다.");
		
		userService.update(makeUser(exist.getId()));
		
		UserView.display("▶ 수정이 완료 되었습니다.");
		
	}

	private void f_insert() {
		System.out.print("유저 ID 입력 :");
		String userName = sc.next();
		while(true) {
			if(userService.selectByName(userName) != null) {
				System.out.println("이미 존재하는 아이디입니다. 다른아이디를 입력하세요.");
				System.out.print("아이디 입력 :");
				userName = sc.next();
			}else {break;}
		}	
	
		System.out.print("비밀번호 입력 :");
		String userPassword = sc.next();
		
		UserDTO user = UserDTO.builder()
		.id(sdf.format(Calendar.getInstance().getTime()))
		.user_id(userName)
		.user_password(userPassword)
		.build();
		
		userService.insert(user);
		UserView.display(user);
		
	}

	private void f_selectAll() {
		List<UserDTO> list = userService.selectAll();
		UserView.display(list);
		
	}

}
