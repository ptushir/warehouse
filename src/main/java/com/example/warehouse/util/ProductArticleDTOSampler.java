package com.example.warehouse.util;

import com.example.warehouse.model.ProductArticleDTO;

public class ProductArticleDTOSampler {

	public static ProductArticleDTO getProductArticleDTO() {
		ProductArticleDTO productArticleDTO = new ProductArticleDTO();
		productArticleDTO.setQuantity(10L);
		return productArticleDTO;
	}
}
