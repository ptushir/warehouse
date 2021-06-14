package com.example.warehouse.controller;

import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import com.example.warehouse.model.BaseRequestDTO;
import com.example.warehouse.service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);
	
	@Autowired
	private InventoryService inventoryService;

	@PostMapping(value = "/addArticles",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> addArticles(@RequestParam("file") MultipartFile file) {
		logger.debug("Inside addArticles.");
		String status = "FAILURE";
	        try {
	        	if (null != file && !file.isEmpty()) {
	        		final ObjectMapper objectMapper = new ObjectMapper();
	        		BaseRequestDTO inventoryDTO = objectMapper.readValue(new InputStreamReader(file.getInputStream()), BaseRequestDTO.class);
	        		status = inventoryService.addArticles(inventoryDTO.getInventoryDTOs());
	        	}
	        }catch (Exception e) {
	        	logger.error("Inventory file processing error." + e.getMessage());
			}
	        logger.debug("Existing addArticles.");
		return ResponseEntity.ok(status);
	}
}
