package com.example.warehouse.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseRequestDTO {

	@JsonProperty("inventory")
	private List<InventoryDTO> inventoryDTOs;

	@JsonProperty("products")
	private List<ProductDTO> productDTOs;

	public List<InventoryDTO> getInventoryDTOs() {
		return inventoryDTOs;
	}

	public void setInventoryDTOs(List<InventoryDTO> inventoryDTOs) {
		this.inventoryDTOs = inventoryDTOs;
	}

	public List<ProductDTO> getProductDTOs() {
		return productDTOs;
	}

	public void setProductDTOs(List<ProductDTO> productDTOs) {
		this.productDTOs = productDTOs;
	}

	@Override
	public String toString() {
		return "BaseRequestDTO [inventoryDTOs=" + inventoryDTOs + ", productDTOs=" + productDTOs + "]";
	}
	
}
