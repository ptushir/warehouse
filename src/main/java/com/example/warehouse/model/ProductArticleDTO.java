package com.example.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductArticleDTO {

	@JsonProperty("art_id")
	private Long articleId;
	
	@JsonProperty("amount_of")
	private Long quantity;

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductArticleDTO [articleId=" + articleId + ", quantity=" + quantity + "]";
	}
}
