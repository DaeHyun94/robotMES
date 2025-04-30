package com.robotMES.admin;

import java.util.List;

public class AdminService {

	AdminDAO adminDAO = new AdminDAO();
	
	public List<AdminDTO> selectAll() {
		return adminDAO.selectAll();
	}

	public AdminDTO selectByName(String adminName) {
		return adminDAO.selectByName(adminName);
	}

	public int insert(AdminDTO Admin) {
		return adminDAO.insert(Admin);
	}

	public int update(AdminDTO Admin) {
		return adminDAO.update(Admin);
		
	}

	public int delete(String Admin) {
		return adminDAO.delete(Admin);
	}

}
