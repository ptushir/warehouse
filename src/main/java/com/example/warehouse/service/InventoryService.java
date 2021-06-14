package com.example.warehouse.service;

import java.util.List;

import com.example.warehouse.model.InventoryDTO;

public interface InventoryService {

	String addArticles(List<InventoryDTO> inventoryDTO);
}
