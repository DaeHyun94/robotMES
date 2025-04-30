package com.rrobotMES.order;

import java.util.List;

import com.robotMES.product.ProductDTO;
import com.robotMES.station.StationDTO;

public class OrderService {

	OrderDAO orderDAO = new OrderDAO();
	
	public List<OrderDTO> selectAll() {
		return orderDAO.selectAll();
	}

	public List<ProductDTO> selectProduct() {
		return orderDAO.selectProduct();
	}

	public List<StationDTO> selectStation() {
		return orderDAO.selectStation();
	}

}
