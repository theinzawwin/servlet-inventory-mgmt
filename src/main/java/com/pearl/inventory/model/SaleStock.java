package com.pearl.inventory.model;

import java.util.Date;

public class SaleStock {

	private Date date;
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
	public Integer getTotal() {
		return getQty()*getPrice();
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	private Integer saleId;
	public Integer getSaleId() {
		return saleId;
	}
	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}
	private Integer productId;
	private String productName;
	private Integer price;
	private Integer qty;
	private Integer total;
	
	public SaleStock() {
		
	}
}
