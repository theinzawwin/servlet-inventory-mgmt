package com.pearl.inventory.model;

public class SaleProductItem {

	private Integer productId;
	private String productName;
	private String code;
	private Integer qty;
	private Integer salePrice;
	
	public SaleProductItem() {
		
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Integer sale_price) {
		this.salePrice = sale_price;
	}

	public SaleProductItem(Integer productId, String productName, String code, Integer qty, Integer sale_price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.code = code;
		this.qty = qty;
		this.salePrice = sale_price;
	}
	
}
