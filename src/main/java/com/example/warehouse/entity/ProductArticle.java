package com.example.warehouse.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "product_article")
public class ProductArticle {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "product_article_SEQ_GENERATOR", sequenceName = "product_article_id_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_article_SEQ_GENERATOR")
	private Long productArticleId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "article_id")
	private Inventory inventory;
	
	@Column(name = "quantity")
	private Long quantity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, optional = false)
	@JoinColumn(name = "product_id")
	private Product product;

	public Long getProductArticleId() {
		return productArticleId;
	}

	public void setProductArticleId(Long productArticleId) {
		this.productArticleId = productArticleId;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "ProductArticle [productArticleId=" + productArticleId + ", inventory=" + inventory + ", quantity="
				+ quantity + "]";
	}

}
