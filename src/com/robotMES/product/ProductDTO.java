package com.robotMES.product;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class ProductDTO {
	private String id;
	private String product_name;
	private String product_type;
	private int goal_count;
	private Date complete_at;
	
	
	@Override
	public String toString() {
		return "|[제품 이름] " + product_name 
				+ "|[제품 타입] " + product_type
				+ "|[목표 수량] " + goal_count 
				+ "|[완료 시간] " + complete_at;
	}
}
