package com.robotMES.robot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RobotDTO {
	String id;
	String robot_name;
	@Override
	public String toString() {
		return "[ID=" + id + ", 로봇이름=" + robot_name + "]";
	}
	
	
}
