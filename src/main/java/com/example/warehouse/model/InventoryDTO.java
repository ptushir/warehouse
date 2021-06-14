package com.example.warehouse.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class InventoryDTO {
	
	@JsonProperty("art_id")
	private Long articleId;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("stock")
	private Long stock;

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "InventoryDTO [articleId=" + articleId + ", name=" + name + ", stock=" + stock + "]";
	}

}
