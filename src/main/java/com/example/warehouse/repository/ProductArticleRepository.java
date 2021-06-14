package com.example.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.ProductArticle;

public interface ProductArticleRepository extends JpaRepository<ProductArticle, Long> {

	List<ProductArticle> findByProduct(Product product);

	void deleteByProduct(Product product);

	void deleteAllByProduct(Product product);

}
