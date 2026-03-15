package com.pearl.inventory.model;

public class Product {

	private Integer productId;
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	private String productName;
	private Integer categoryId;
	private String categoryName;
	private String code;
	
	public Product(Integer productId, String productName, Integer categoryId,String code, String categoryName) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.code = code;
	}
	
	public Product(Integer productId, String productName, Integer categoryId,String code) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.categoryId = categoryId;
		this.code = code;
	}
	
	public Product() {
		
	}
	
}
