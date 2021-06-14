package com.example.warehouse.controller;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.warehouse.model.ProductDTO;
import com.example.warehouse.service.ProductService;
import com.example.warehouse.util.ProductDTOSampler;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

	@InjectMocks
	private ProductController productController;
	
	@Mock
	private ProductService productService;
	
	private static ProductDTO productDTO;
	
	@BeforeAll
	public static void init() {
		productDTO = ProductDTOSampler.getProductDTO();
	}
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getProducts() throws SQLException {
		//Mockito.when(productService.getProducts()(Mockito.any(ProductDTO.class))).thenReturn(productDTO);
		
	}
}
