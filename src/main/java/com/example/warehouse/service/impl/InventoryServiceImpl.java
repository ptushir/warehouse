package com.example.warehouse.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.warehouse.entity.Inventory;
import com.example.warehouse.model.InventoryDTO;
import com.example.warehouse.repository.InventoryRepository;
import com.example.warehouse.service.InventoryService;

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService{
	
	@Autowired
	private InventoryRepository inventoryRepository;

	@Override
	@Transactional
	public String addArticles(List<InventoryDTO> inventoryDTO) {
		 inventoryRepository.saveAll(inventoryDTO.parallelStream().map(inventory-> convertToEntity(inventory)).collect(Collectors.toList()));
	     return "SUCCESS";
	}
	
	private Inventory convertToEntity(InventoryDTO inventoryDTO) {
		Inventory inventory = new Inventory();
		inventory.setId(inventoryDTO.getArticleId());
		inventory.setName(inventoryDTO.getName());
		inventory.setStock(inventoryDTO.getStock());
		return inventory;
	}
}
