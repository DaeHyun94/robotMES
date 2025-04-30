package com.robotMES.product;

import java.util.List;

public class ProductService {

	ProductDAO productDAO = new ProductDAO();
	
	public List<ProductDTO> selectAll() {
		return productDAO.selectAll();
	}

	public ProductDTO selectByName(String productName) {
		return productDAO.selectByName(productName);
	}

	public int insert(ProductDTO product) {
		return productDAO.insert(product);
	}

	public int update(ProductDTO product) {
		return productDAO.update(product);
		
	}

	public int delete(String productName) {
		return productDAO.delete(productName);
	}

}
