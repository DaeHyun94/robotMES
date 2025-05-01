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

	public int insert(OrderDTO orderDTO) {
		return orderDAO.insert(orderDTO);
	}

	public int delete(String admin) {
		
		return orderDAO.delete(admin);
	}

	public OrderDTO selectByName(String orderID) {
	
		return orderDAO.selectByName(orderID);
	}

	public int update(OrderDTO order) {
		return orderDAO.update(order);
		
	}

}
