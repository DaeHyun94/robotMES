package com.robotMES.product;

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

public class ProductDAO {

	public List<ProductDTO> selectAll() {
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
				ProductDTO product = makeProduct(rs);
				productlist.add(product);
			}
		}catch(SQLException e){ e.printStackTrace();}
		finally { DBUtil.dbDisconnect(conn, st, rs);}
			
		return productlist;
	}

	public ProductDTO makeProduct(ResultSet rs) throws SQLException {
		
		ProductDTO product = ProductDTO.builder()
				.id(rs.getString("id"))
				.product_name(rs.getString("product_name"))
				.product_type(rs.getString("product_type"))
				.goal_count(rs.getInt("goal_count"))
				.complete_at(rs.getDate("complete_at"))
				.build();
		
		return product;
		
	}

	public ProductDTO selectByName(String productName) {
		ProductDTO product = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from tb_product where product_name = ?";
		try {
			st = conn.prepareStatement(sql); 
			st.setString(1, productName); 
			rs = st.executeQuery();
			
			if (rs.next()) {
				product = makeProduct(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return product;
	}

	public int insert(ProductDTO product) {
		
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		
		String sql = """
					insert into tb_product(
					ID, 
					product_name, 
					product_type,
					goal_count )
					values(?,?,?,?)
					""";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1,product.getId());
			st.setString(2,product.getProduct_name());
			st.setString(3,product.getProduct_type());
			st.setInt(4, product.getGoal_count());
			result = st.executeUpdate();
			
		}catch(SQLException e){ e.printStackTrace();}
		finally { DBUtil.dbDisconnect(conn, st);}
		
		
		return result;
	}

	public int update(ProductDTO productDTO) {
		
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		Map<String, Object> dynamicSQL = new HashMap<>();
		
		if (productDTO.getProduct_name() != null)
			dynamicSQL.put("product_name", productDTO.getProduct_name());
		if (productDTO.getProduct_type() != null)
			dynamicSQL.put("product_type", productDTO.getProduct_type());
		if (productDTO.getGoal_count() == 0)
			dynamicSQL.put("goal_count", productDTO.getGoal_count());
		
		String sql = " update tb_product set ";
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
			st.setString(i, productDTO.getId());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int delete(String productName) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = "delete from tb_product where product_name = ?";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, productName);
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
