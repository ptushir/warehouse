package com.example.warehouse.controller;

import java.io.InputStreamReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.warehouse.model.BaseRequestDTO;
import com.example.warehouse.model.ProductDTO;
import com.example.warehouse.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/product")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(value = "/addProducts",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> addProducts(@RequestParam("file") MultipartFile file) {
		logger.info("Inside addProducts.");
		String status = "FAILURE";
        try {
        	if (null != file && !file.isEmpty()) {
        		final ObjectMapper objectMapper = new ObjectMapper();
        		BaseRequestDTO requestDTO = objectMapper.readValue(new InputStreamReader(file.getInputStream()), BaseRequestDTO.class);
        		status = productService.addProducts(requestDTO.getProductDTOs());
        	}
        }catch (Exception e) {
        	logger.error("Product file processing error: " + e.getMessage());
		}
        logger.info("Existing addProducts.");
		return ResponseEntity.ok(status);
	}
	
	@GetMapping("/getProducts")
	public ResponseEntity<List<ProductDTO>> getProducts() {
		logger.info("Inside getProducts.");
		List<ProductDTO> productDTOs = productService.getProducts();
		
		logger.info("Existing getProducts.");
		return ResponseEntity.ok(productDTOs);
	}
	
	@DeleteMapping("/sellProduct/{productId}")
	public ResponseEntity<String> sellProduct(@PathVariable("productId") Long productId) {
		logger.debug("Inside sellProduct : {}", productId);
		String status = "FAILURE";
		status = productService.sellProduct(productId);
		logger.debug("Existing sellProduct.");
		return ResponseEntity.ok(status);	}
}
