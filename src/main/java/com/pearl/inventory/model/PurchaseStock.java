package com.pearl.inventory.model;

import java.util.Date;

public class PurchaseStock {

	private Integer purchaseId;
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	private Date date;
	private Integer productId;
	private String productName;
	private Integer price;
	private Integer qty;
	private Integer total;
	
	public PurchaseStock(Integer purchaseId, Date date, Integer productId, String productName, Integer price,
			Integer qty) {
		super();
		this.purchaseId = purchaseId;
		this.date = date;
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.qty = qty;
	}
	public Integer getTotal() {
		return this.qty*this.price;
	}
	public PurchaseStock() {
		
	}
	
}
