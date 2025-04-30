package com.robotMES.admin;

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

public class AdminDAO {

	public List<AdminDTO> selectAll() {
		List<AdminDTO> list = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		String sql = """
					select *
					from tb_Admin
					""";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				AdminDTO admin = makeAdmin(rs);
				list.add(admin);
			}
		}catch(SQLException e){ e.printStackTrace();}
		finally { DBUtil.dbDisconnect(conn, st, rs);}
			
		return list;
	}

	private AdminDTO makeAdmin(ResultSet rs) throws SQLException {
		AdminDTO admin = AdminDTO.builder()
				.id(rs.getString("id"))
				.admin_id(rs.getString("admin_id"))
				.admin_password(rs.getString("admin_password"))
				.build();
				
		return admin;
	}

	public AdminDTO selectByName(String adminName) {
		AdminDTO admin = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from tb_Admin where Admin_id = ?";
		try {
			st = conn.prepareStatement(sql); 
			st.setString(1, adminName); 
			rs = st.executeQuery();
			
			if (rs.next()) {
				admin = makeAdmin(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return admin;
	}

	public int insert(AdminDTO admin) {
		
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		
		String sql = """
					insert into tb_admin(
					ID, 
					admin_id, 
					admin_password)
					values(?,?,?)
					""";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1,admin.getId());
			st.setString(2,admin.getAdmin_id());
			st.setString(3,admin.getAdmin_password());
			result = st.executeUpdate();
			
		}catch(SQLException e){ e.printStackTrace();}
		finally { DBUtil.dbDisconnect(conn, st);}
		
		
		return result;
	}

	public int update(AdminDTO admin) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		Map<String, Object> dynamicSQL = new HashMap<>();
		
		if (admin.getAdmin_id() != null)
			dynamicSQL.put("admin_id", admin.getAdmin_id());
		if (admin.getAdmin_password() != null)
			dynamicSQL.put("admin_password", admin.getAdmin_password());
		
		String sql = " update tb_admin set ";
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
			st.setString(i, admin.getId());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int delete(String admin) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = "delete from tb_admin where admin_id = ?";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, admin);
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
