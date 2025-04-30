package com.robotMES.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO {
	
	private String id;
	private String user_id;
	private String user_password;
	
	@Override
	public String toString() {
		return "[ID=" + id + ", 유저 ID =" + user_id + ", 유저 PW =" + user_password + "]";
	}
	
	
}
