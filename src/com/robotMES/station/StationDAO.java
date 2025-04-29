package com.robotMES.station;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Util.DBUtil;

public class StationDAO {

	public List<StationDTO> selectAllStation() {
		List<StationDTO> stationlist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		String sql = """
					select *
					from tb_station
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

	public int stationInsert(StationDTO station) {
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


	private StationDTO makeStation(ResultSet rs) throws SQLException {
		StationDTO station = StationDTO.builder()
				.id(rs.getString("id"))
				.station_name(rs.getString("station_name"))
				.station_type(rs.getString("station_type"))
				.build();
		return station;
	}

	

}
