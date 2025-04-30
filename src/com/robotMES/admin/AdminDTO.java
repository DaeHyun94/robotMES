package com.robotMES.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AdminDTO {
	
	private String id;
	private String admin_id;
	private String admin_password;
	@Override
	public String toString() {
		return "[id=" + id + ", 관리자ID =" + admin_id + ", 관리자PW=" + admin_password + "]";
	}
	
	
}
