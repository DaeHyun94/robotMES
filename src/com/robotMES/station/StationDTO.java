package com.robotMES.station;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StationDTO {

	private String id;
	private String station_name;
	private String station_type;
	
	@Override
	public String toString() {
		return "[Id =" + id + ", "
				+ "작업대 이름=" + station_name 
				+ ", 작업대 타입=" + station_type 
				+ "]";
	}
	
	
}
