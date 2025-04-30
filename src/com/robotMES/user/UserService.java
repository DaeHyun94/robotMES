package com.robotMES.user;

import java.util.List;

public class UserService {

	UserDAO userDAO = new UserDAO();
	
	public List<UserDTO> selectAll() {
		return userDAO.selectAll();
	}

	public UserDTO selectByName(String user) {
		return userDAO.selectByName(user);
	}

	public int insert(UserDTO user) {
		return userDAO.insert(user);
	}

	public int update(UserDTO user) {
		return userDAO.update(user);
		
	}

	public int delete(String user) {
		return userDAO.delete(user);
	}

}
