package com.robotMES.robotControll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Util.DBUtil;

public class RobotControllDAO {

	public static List<RobotControllDTO> selectAll() {
		List<RobotControllDTO> list = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		String sql = """
					select *
					from tb_robotControll
					""";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				RobotControllDTO robot = makeRobot(rs);
				list.add(robot);
			}
		}catch(SQLException e){ e.printStackTrace();}
		finally { DBUtil.dbDisconnect(conn, st, rs);}
			
		return list;
	}

	private static RobotControllDTO makeRobot(ResultSet rs) throws SQLException {
		RobotControllDTO robot = RobotControllDTO.builder()
				.id(rs.getString("id"))
				.robot_name(rs.getString("robot_name"))
				.status(rs.getString("status"))
				.bettery_level(rs.getInt("bettery_level"))
				.isemergency(rs.getInt("isemergency"))
				.speed(rs.getInt("speed"))
				.build();
				
		return robot;
	}

}
