package com.robotMES.robotControll;

import java.util.List;

public class RobotControllService {

	public List<RobotControllDTO> selectAll() {
		return RobotControllDAO.selectAll();
	}

}
