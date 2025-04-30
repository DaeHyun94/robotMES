package com.robotMES.robot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.DBUtil;

public class RobotDAO {

	public List<RobotDTO> selectAll() {
		List<RobotDTO> robotlist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		String sql = """
					select *
					from tb_robot
					order by robot_name
					""";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				RobotDTO station = makeRobot(rs);
				robotlist.add(station);
			}
		}catch(SQLException e){ e.printStackTrace();}
		finally { DBUtil.dbDisconnect(conn, st, rs);}
			
		return robotlist;
	}

	public int insert(RobotDTO robotDTO) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		
		String sql = """
					insert into tb_robot(
					ID, 
					robot_name)
					values(?,?)
					""";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1,robotDTO.getId());
			st.setString(2,robotDTO.getRobot_name());
			result = st.executeUpdate();
			
		}catch(SQLException e){ e.printStackTrace();}
		finally { DBUtil.dbDisconnect(conn, st);}
		
		
		return result;
	}

	public RobotDTO selectByName(String robotName) {
		RobotDTO robot = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from tb_robot where robot_name = ?";
		try {
			st = conn.prepareStatement(sql); // SQL문을 준비한다.
			st.setString(1, robotName); // 1번째 ?에 값을 setting한다.
			rs = st.executeQuery();
			
			if (rs.next()) {
				robot = makeRobot(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return robot;
	}

	private RobotDTO makeRobot(ResultSet rs) throws SQLException {
		RobotDTO robot = RobotDTO.builder()
				.id(rs.getString("id"))
				.robot_name(rs.getString("robot_name"))
				.build();
		return robot;
	}

	public int update(RobotDTO robotDTO) {
		
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		Map<String, Object> dynamicSQL = new HashMap<>();
		
		if (robotDTO.getRobot_name() != null)
			dynamicSQL.put("robot_name", robotDTO.getRobot_name());
		
		
		String sql = " update tb_robot set ";
		String sql2 = " where id = ? ";
		for (String key : dynamicSQL.keySet()) {
			sql += key + "=" + "?,"; // salary=?,email=?,
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += sql2;
		//System.out.println(sql);

		try {
			st = conn.prepareStatement(sql);
			int i = 1;
			for (String key : dynamicSQL.keySet()) {
				st.setObject(i++, dynamicSQL.get(key));
			}
			st.setString(i, robotDTO.getId());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int delete(String robotName) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = "delete from tb_robot where robot_name = ?";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, robotName);
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
