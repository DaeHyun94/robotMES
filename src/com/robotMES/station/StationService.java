package com.robotMES.station;

import java.util.List;

public class StationService {

	StationDAO stationDAO = new StationDAO();
	
	public List<StationDTO> selectAllStation() {
		return stationDAO.selectAllStation();
	}

	public int stationInsert(StationDTO stationDTO) {
		return stationDAO.stationInsert(stationDTO);
		
	}

}
