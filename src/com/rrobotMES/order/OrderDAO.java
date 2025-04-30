package com.rrobotMES.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.robotMES.product.ProductDAO;
import com.robotMES.product.ProductDTO;
import com.robotMES.station.StationDAO;
import com.robotMES.station.StationDTO;

import Util.DBUtil;

public class OrderDAO {

	public List<OrderDTO> selectAll() {
		List<OrderDTO> list = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		String sql = """
					select *
					from tb_order
					order by order_at
					""";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				OrderDTO order = makeOrder(rs);
				list.add(order);
			}
		}catch(SQLException e){ e.printStackTrace();}
		finally { DBUtil.dbDisconnect(conn, st, rs);}
			
		return list;
	}

	private OrderDTO makeOrder(ResultSet rs) throws SQLException {
		OrderDTO order = OrderDTO.builder()
				.id(rs.getString("id"))
				.admin_id(rs.getString("admin_id"))
				.product_id(rs.getString("product_id"))
				.robot_id(rs.getString("robot_id"))
				.from_station_id(rs.getString("from_station_id"))
				.to_station_id(rs.getString("to_station_id"))
				.product_count(rs.getInt("product_count"))
				.from_station_signal(rs.getInt("from_station_signal"))
				.to_station_signal(rs.getInt("to_station_signal"))
				.order_at(rs.getDate("order_at"))
				.complete_at(rs.getDate("complete_at"))
				.build();
		return order;
	}

	public List<ProductDTO> selectProduct() {
		ProductDAO productDAO = new ProductDAO();
		List<ProductDTO> productlist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		String sql = """
					select *
					from tb_product
					order by product_type, product_name
					""";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ProductDTO product = productDAO.makeProduct(rs);
				productlist.add(product);
			}
		}catch(SQLException e){ e.printStackTrace();}
		finally { DBUtil.dbDisconnect(conn, st, rs);}
			
		return productlist;
	}

	public List<StationDTO> selectStation() {
		StationDAO stationDAO = new StationDAO();
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
				StationDTO station = stationDAO.makeStation(rs);
				stationlist.add(station);
			}
		}catch(SQLException e){ e.printStackTrace();}
		finally { DBUtil.dbDisconnect(conn, st, rs);}
			
		return stationlist;
	}

}
