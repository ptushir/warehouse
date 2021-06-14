package com.example.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.warehouse.entity.ProductArticle;

public interface ProductArticleRepository extends JpaRepository<ProductArticle, Long> {

	@Query(value = "SELECT s FROM ProductArticle s WHERE s.product= :productId")
	List<ProductArticle> findByProduct(Long productId);

}
