package com.robotMES.robotControll;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class RobotControllDTO {

	private String id;
	private String robot_name;
	private String status;
	private int bettery_level;
	private int isemergency;
	private int speed;
	
	
	@Override
	public String toString() {
		return "[id=" + id + ", robot_name=" + robot_name + ", status=" + status + ", bettery_level="
				+ bettery_level + ", isemergency=" + isemergency + ", speed=" + speed + "]";
	}
	
	
	
	
	
}
