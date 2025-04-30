package com.robotMES.station;

import java.util.List;

public class StationService {

	StationDAO stationDAO = new StationDAO();
	
	public List<StationDTO> selectAll() {
		return stationDAO.selectAll();
	}

	public int insert(StationDTO stationDTO) {
		return stationDAO.insert(stationDTO);
		
	}

	public int delete(String stationID) {
		return stationDAO.delete(stationID);
	}

	public StationDTO selectByName(String stationName) {
		return stationDAO.selectByName(stationName);
	}

	public int update(StationDTO stationDTO) {
		return stationDAO.update(stationDTO);
	}

}
