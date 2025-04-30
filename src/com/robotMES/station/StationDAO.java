package com.robotMES.station;

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

public class StationDAO {

	public List<StationDTO> selectAll() {
		List<StationDTO> stationlist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		String sql = """
					select *
					from tb_station
					order by station_type, station_name
					""";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				StationDTO station = makeStation(rs);
				stationlist.add(station);
			}
		}catch(SQLException e){ e.printStackTrace();}
		finally { DBUtil.dbDisconnect(conn, st, rs);}
			
		return stationlist;
	}

	public int insert(StationDTO station) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		
		String sql = """
					insert into tb_station(
					ID, 
					station_name, 
					station_type )
					values(?,?,?)
					""";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1,station.getId());
			st.setString(2,station.getStation_name());
			st.setString(3,station.getStation_type());
			result = st.executeUpdate();
			
		}catch(SQLException e){ e.printStackTrace();}
		finally { DBUtil.dbDisconnect(conn, st);}
		
		
		return result;
	}

	public int delete(String stationName) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = "delete from tb_station where station_name = ?";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, stationName);
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public StationDTO selectByName(String stationName) {
		StationDTO station = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from tb_station where station_name = ?";
		try {
			st = conn.prepareStatement(sql); // SQL문을 준비한다.
			st.setString(1, stationName); // 1번째 ?에 값을 setting한다.
			rs = st.executeQuery();
			
			if (rs.next()) {
				station = makeStation(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return station;
	}

	public int update(StationDTO stationDTO) {
		
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		Map<String, Object> dynamicSQL = new HashMap<>();
		
		if (stationDTO.getStation_name() != null)
			dynamicSQL.put("station_name", stationDTO.getStation_name());
		if (stationDTO.getStation_type() != null)
			dynamicSQL.put("station_type", stationDTO.getStation_type());
		
		String sql = " update tb_station set ";
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
			st.setString(i, stationDTO.getId());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	
	}

	public StationDTO makeStation(ResultSet rs) throws SQLException {
		StationDTO station = StationDTO.builder()
				.id(rs.getString("id"))
				.station_name(rs.getString("station_name"))
				.station_type(rs.getString("station_type"))
				.build();
		return station;
	}

}
