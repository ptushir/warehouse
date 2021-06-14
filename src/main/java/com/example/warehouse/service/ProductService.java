package com.example.warehouse.service;

import java.util.List;

import com.example.warehouse.model.ProductDTO;

public interface ProductService {
	
	String addProducts(List<ProductDTO> productDTOs);
	
	List<ProductDTO> getProducts();
	
	String sellProduct(Long productId);

}
