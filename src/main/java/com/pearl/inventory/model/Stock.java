package com.pearl.inventory.model;

public class Stock {

	private Integer stockId;
	private Integer productId;
	private String productName;
	private Integer purchasePrice;
	private Integer salePrice;
	private Integer qty;
	
	public Stock() {
		
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

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

	public Integer getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Integer purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Integer getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Integer salePrice) {
		this.salePrice = salePrice;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Stock(Integer stockId, Integer productId, String productName, Integer purchasePrice, Integer salePrice,
			Integer qty) {
		super();
		this.stockId = stockId;
		this.productId = productId;
		this.productName = productName;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.qty = qty;
	}
	
}
