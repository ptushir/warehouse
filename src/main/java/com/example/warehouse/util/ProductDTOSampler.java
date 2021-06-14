package com.example.warehouse.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.warehouse.model.ProductArticleDTO;
import com.example.warehouse.model.ProductDTO;

public class ProductDTOSampler {

	public static ProductDTO getProductDTO() {
		ProductDTO productDTO = new ProductDTO();
		List<ProductArticleDTO> productArticleDTOSet = new ArrayList<>();
		productDTO.setProductName("Chair");
		productDTO.setProductPrice(new BigDecimal(199));
		
		productArticleDTOSet.add(ProductArticleDTOSampler.getProductArticleDTO());
		productDTO.setArticles(productArticleDTOSet);
		return productDTO;
	}
}
