package com.robotMES.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robotMES.admin.AdminDTO;

import Util.DBUtil;

public class UserDAO {

	public List<UserDTO> selectAll() {
		List<UserDTO> list = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;

		String sql = """
				select *
				from tb_User
				""";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				UserDTO user = makeUser(rs);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}

		return list;
	}

	private UserDTO makeUser(ResultSet rs) throws SQLException {
		UserDTO user = UserDTO.builder().id(rs.getString("id")).user_id(rs.getString("user_id"))
				.user_password(rs.getString("user_password")).build();

		return user;
	}

	public UserDTO selectByName(String userName) {
		UserDTO user = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from tb_user where user_id = ?";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, userName);
			rs = st.executeQuery();

			if (rs.next()) {
				user = makeUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return user;
	}

	public int insert(UserDTO user) {

		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;

		String sql = """
				insert into tb_user(
				ID,
				user_id,
				user_password)
				values(?,?,?)
				""";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, user.getId());
			st.setString(2, user.getUser_id());
			st.setString(3, user.getUser_password());
			result = st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st);
		}

		return result;
	}

	public int update(UserDTO user) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		Map<String, Object> dynamicSQL = new HashMap<>();

		if (user.getUser_id() != null)
			dynamicSQL.put("user_id", user.getUser_id());
		if (user.getUser_password() != null)
			dynamicSQL.put("user_password", user.getUser_password());

		String sql = " update tb_user set ";
		String sql2 = " where id = ? ";
		for (String key : dynamicSQL.keySet()) {
			sql += key + "=" + "?,";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += sql2;
		// System.out.println(sql);

		try {
			st = conn.prepareStatement(sql);
			int i = 1;
			for (String key : dynamicSQL.keySet()) {
				st.setObject(i++, dynamicSQL.get(key));
			}
			st.setString(i, user.getId());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int delete(String user) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = "delete from tb_user where user_id = ?";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, user);
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
