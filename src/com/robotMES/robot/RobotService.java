package com.robotMES.robot;

import java.util.List;

public class RobotService {

	RobotDAO robotDAO = new RobotDAO();
	
	public List<RobotDTO> selectAll() {
		return robotDAO.selectAll();
	}

	public int insert(RobotDTO robotDTO) {
		return robotDAO.insert(robotDTO);
		
	}

	public RobotDTO selectByName(String robotName) {
		return robotDAO.selectByName(robotName);
	}

	public int update(RobotDTO robotDTO) {
		return robotDAO.update(robotDTO);
		
	}

	public int delete(String robotId) {
		return robotDAO.delete(robotId);
	}
}
