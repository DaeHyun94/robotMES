package com.rrobotMES.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				.user_id(rs.getString("user_id"))
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

	public int insert(OrderDTO orderDTO) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		
		String sql = """
					insert into tb_order(
					ID,
					admin_id, 
					product_id, 
					from_station_id,
					to_station_id,
					 product_count,
					 order_at)
					values(?,?,?,?,?,?,?)
					""";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1,orderDTO.getId());
			st.setString(2,orderDTO.getAdmin_id());
			st.setString(3,orderDTO.getProduct_id());
			st.setString(4, orderDTO.getFrom_station_id());
			st.setString(5, orderDTO.getTo_station_id());
			st.setInt(6, orderDTO.getProduct_count());
			st.setDate(7, orderDTO.getOrder_at());
			
			result = st.executeUpdate();
			
		}catch(SQLException e){ e.printStackTrace();}
		finally { DBUtil.dbDisconnect(conn, st);}
		
		
		return result;
	}

	public int delete(String admin) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = "delete from tb_order where admin_id = ?";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, admin);
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public OrderDTO selectByName(String orderID) {
		OrderDTO order = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from tb_Admin where Order_id = ?";
		try {
			st = conn.prepareStatement(sql); 
			st.setString(1, orderID); 
			rs = st.executeQuery();
			
			if (rs.next()) {
				order = makeOrder(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return order;
	}

	public int update(OrderDTO order) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		Map<String, Object> dynamicSQL = new HashMap<>();
		
		if (order.getUser_id() != null)
			dynamicSQL.put("user_id", order.getUser_id());
		
		String sql = " update tb_order set ";
		String sql2 = " where id = ? ";
		for (String key : dynamicSQL.keySet()) {
			sql += key + "=" + "?,";
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
			st.setString(i, order.getId());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, st);
		}
		return result;
	}

	

}
