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
	
	
}
