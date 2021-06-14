package com.example.warehouse.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.warehouse.entity.Inventory;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.ProductArticle;
import com.example.warehouse.model.ProductArticleDTO;
import com.example.warehouse.model.ProductDTO;
import com.example.warehouse.repository.InventoryRepository;
import com.example.warehouse.repository.ProductArticleRepository;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService{

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private ProductArticleRepository productArticleRepository;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public String addProducts(List<ProductDTO> productDTOs) {
		logger.debug("Inside ProductServiceImpl's addProducts.");
		Set<Long> inventoryIds = productDTOs.stream().map(productDTO -> filterArticleIds(productDTO.getArticles())).flatMap(s -> s.stream()).collect(Collectors.toSet());
		List<Inventory> inventoryList = inventoryRepository.findAllById(inventoryIds);
		if(inventoryList.size() < inventoryIds.size()) {
			logger.debug("Article does not exist in Inventory.");
			System.out.println("Article does not exist in Inventory.");
			return "FAILURE";
		}
		
		productDTOs.stream().forEach(productDTO -> {
			Product product = productRepository.save(convertToEntity(productDTO));
			productArticleRepository.saveAll(productDTO.getArticles().stream().map(article -> convertToProductArticleEntity(article, product)).collect(Collectors.toList()));
		});
		
		logger.debug("Existing ProductServiceImpl's addProducts.");
		return "SUCCESS";
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public List<ProductDTO> getProducts() {
		logger.debug("Inside ProductServiceImpl's getProducts.");
		
		Map<Product, List<ProductArticle>> productArticlesMap = productArticleRepository.findAll().stream().collect(Collectors.groupingBy(p -> p.getProduct(), Collectors.toList()));
		List<ProductDTO> productDTOs = convertToProductDTO(productArticlesMap);
		
		logger.debug("Existing ProductServiceImpl's addProducts.");
		return productDTOs;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public String sellProduct(Long productId) {
		logger.debug("Inside ProductServiceImpl's sellProduct.");
		Optional<Product> product = productRepository.findById(productId);
		if(!product.isPresent()) {
			return "FAILURE, Product does not exist.";
		}else {
			List<ProductArticle> productArticleList = productArticleRepository.findByProduct(product.get());
			if(productArticleList.isEmpty()) {
				logger.debug("No Product found!");
				return "NO Product Data Found!";
			}else {
				// get inventory articles and subtract product article from them.
				List<Inventory> inventoryArticles = new ArrayList<>();
				for(ProductArticle productArticle : productArticleList) {
					Inventory inventory = productArticle.getInventory();
					inventory.setStock(inventory.getStock() - productArticle.getQuantity());
					inventoryArticles.add(inventory);
				}
				
				productArticleRepository.deleteAllByProduct(productArticleList.get(0).getProduct());
				productRepository.deleteById(productArticleList.get(0).getProduct().getProductId());
				inventoryRepository.saveAll(inventoryArticles);
			}
		}
		logger.debug("Existing ProductServiceImpl's sellProduct.");
		return "SUCCESS, Removed successfully";
	}

	private Product convertToEntity(ProductDTO productDTO) {
		Product product = new Product();
		product.setProductName(productDTO.getProductName());
		product.setPrice(productDTO.getProductPrice());
		return product;
	}
	
	private List<ProductDTO> convertToProductDTO(Map<Product, List<ProductArticle>> productArticleMap) {
		List<ProductDTO> productDTOs = new ArrayList<>();
		
		for (Map.Entry<Product, List<ProductArticle>> entry : productArticleMap.entrySet()) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProductId(entry.getKey().getProductId());
			productDTO.setProductName(entry.getKey().getProductName());
			productDTO.setProductPrice(entry.getKey().getPrice());
			productDTO.setArticles(convertToProductArticleDTO(entry.getValue()));
			productDTOs.add(productDTO);
		}
		
		return productDTOs;
	}
	
	private List<ProductArticleDTO> convertToProductArticleDTO(List<ProductArticle> productArticlesList) {
		List<ProductArticleDTO> productArticleDTOList = new ArrayList<>();
		
		for(ProductArticle productArticle : productArticlesList) {
			ProductArticleDTO productArticleDTO = new ProductArticleDTO();
			productArticleDTO.setArticleId(productArticle.getInventory().getId());
			productArticleDTO.setQuantity(productArticle.getQuantity());
			productArticleDTOList.add(productArticleDTO);
		}
		
		return productArticleDTOList;
	}
	
	private ProductArticle convertToProductArticleEntity(ProductArticleDTO productArticleDTO, Product product){
		ProductArticle productArticle = new ProductArticle();
		productArticle.setQuantity(productArticleDTO.getQuantity());
		productArticle.setInventory(inventoryRepository.getById(productArticleDTO.getArticleId()));
		productArticle.setProduct(product);
		return productArticle;
	}
	
	private List<Long> filterArticleIds(List<ProductArticleDTO> productArticleDTOs){
		return productArticleDTOs.stream().map(productArticleDTO -> productArticleDTO.getArticleId()).collect(Collectors.toList());
	}
}
