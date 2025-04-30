package com.rrobotMES.order;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor 
public class OrderDTO {
	private String id;
	private String admin_id;
	private String user_id;
	private String product_id;
	private String robot_id;
	private String from_station_id;
	private String to_station_id;
	private int product_count;
	private int from_station_signal;
	private int to_station_signal;
	private Date order_at;
	private Date complete_at;
	
	
	@Override
	public String toString() {
		return	"◎ 관리자 =" + admin_id 
				+ ", 작업자 =" + user_id 
				+ ", 제품 =" + product_id
				+ ", 담당 로봇=" + robot_id 
				+ ", 시작 공정=" + from_station_id 
				+ ", 종료 공정=" + to_station_id
				+ ", 목표 수량=" + product_count 
				+ ", 시작 공정 OK 신호=" + from_station_signal
				+ ", 완료 공정 OK 신호=" + to_station_signal 
				+ ", 오더 생성 시간=" + order_at 
				+ ", 오더 완료 시간=" + complete_at
				+ "";
	}
}
