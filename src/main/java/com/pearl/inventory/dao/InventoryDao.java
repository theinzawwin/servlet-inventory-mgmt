package com.pearl.inventory.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.pearl.inventory.model.PurchaseStock;
import com.pearl.inventory.model.SaleStock;
import com.pearl.inventory.model.Stock;

public class InventoryDao {

	private static final String URL = "jdbc:mysql://localhost:3306/pearl_inventory";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
	public boolean savePurchase(PurchaseStock purchaseStock) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO purchase_stock (product_id, price,qty,date) VALUES (?, ?,?,?)";

		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(purchaseStock.getDate().getTime());
		java.sql.Date sqlDate = new java.sql.Date(sqlTimestamp.getTime());
		try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, purchaseStock.getProductId());
            stmt.setInt(2, purchaseStock.getPrice());
            stmt.setInt(3,purchaseStock.getQty());
            stmt.setDate(4, sqlDate);
           
            stmt.executeUpdate();
        }
		return true;
	}
	
	public boolean updateStock(Stock stock) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE stock SET sale_price=?, purchase_price=?,qty=?, product_id=? WHERE stock_id=?";

	
		try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, stock.getSalePrice());
            stmt.setInt(2, stock.getPurchasePrice());
            stmt.setInt(3,stock.getQty());
            stmt.setInt(4, stock.getProductId());
            stmt.setInt(5, stock.getStockId());
           
            stmt.executeUpdate();
        }
		return true;
	}
	
	public boolean saveStock(Stock stock) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO stock (product_id, qty,sale_price,purchase_price) VALUES (?, ?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, stock.getProductId());
            stmt.setInt(2, stock.getQty());
            stmt.setInt(3,stock.getSalePrice());
            stmt.setInt(4, stock.getPurchasePrice());
           
            stmt.executeUpdate();
        }
		return true;
	}
	public Stock getStockByProductId(Integer productId) throws ClassNotFoundException, SQLException {
		String sql="SELECT * from stock where product_id=?";
		Stock stock =null;
        try (Connection conn = getConnection();
        		
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
        		stmt.setInt(1, productId);
             ResultSet rs = stmt.executeQuery();
             
            while (rs.next()) {
            	stock = new Stock();
            	stock.setStockId(rs.getInt("stock_id"));
            	stock.setProductId(rs.getInt("product_id"));
            	stock.setPurchasePrice(rs.getInt("purchase_price"));
            	stock.setSalePrice(rs.getInt("purchase_price"));
            	stock.setQty(rs.getInt("qty"));
            	
            }
        }
        return stock;
	}
	public List<Stock> getAllStock() throws ClassNotFoundException, SQLException{
		String sql="SELECT * from stock s join product p on s.product_id=p.product_id";
		List<Stock> stockList = new ArrayList<>();
        try (Connection conn = getConnection();
        		
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
        		
             ResultSet rs = stmt.executeQuery();
             
            while (rs.next()) {
            	
            	Stock stock = new Stock();
            	stock.setProductId(rs.getInt("product_id"));
            	stock.setPurchasePrice(rs.getInt("purchase_price"));
            	stock.setSalePrice(rs.getInt("purchase_price"));
            	stock.setQty(rs.getInt("qty"));
            	stockList.add(stock);
            }
        }
        return stockList;
	}
	
	public List<PurchaseStock> getAllPurchaseList() throws SQLException, ClassNotFoundException{
		String sql="select ps.*,p.product_name from purchase_stock ps left join product p on ps.product_id=p.product_id";
		List<PurchaseStock> purchaseStockList = new ArrayList<PurchaseStock>();
        try (Connection conn = getConnection();
        		
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
        		
             ResultSet rs = stmt.executeQuery();
             
            while (rs.next()) {
            	PurchaseStock ps = new PurchaseStock();
            	ps.setPurchaseId(rs.getInt("purchase_id"));
            	ps.setProductId(rs.getInt("product_id"));
            	ps.setProductName(rs.getString("product_name"));
            	ps.setQty(rs.getInt("qty"));
            	ps.setPrice(rs.getInt("price"));
            	ps.setDate(rs.getDate("date"));
            	purchaseStockList.add(ps);
            }
        }
        return purchaseStockList;
	}
	// Save Sale Stock
	public boolean saveSaleStock(SaleStock saleStock) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO sale_stock (product_id, price,qty,date) VALUES (?, ?,?,?)";

		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(saleStock.getDate().getTime());
		java.sql.Date sqlDate = new java.sql.Date(sqlTimestamp.getTime());
		try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, saleStock.getProductId());
            stmt.setInt(2, saleStock.getPrice());
            stmt.setInt(3,saleStock.getQty());
            stmt.setDate(4, sqlDate);
           
            stmt.executeUpdate();
        }
		return true;
	}
	
	public List<SaleStock> getAllSaleList() throws SQLException, ClassNotFoundException{
		String sql="select ss.sale_id,ss.date,ss.qty,ss.price,p.product_id,p.product_name from sale_stock ss left join product p on ss.product_id=p.product_id";
		List<SaleStock> saleStockList = new ArrayList<SaleStock>();
        try (Connection conn = getConnection();
        		
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
        		
             ResultSet rs = stmt.executeQuery();
             
            while (rs.next()) {
            	SaleStock ss = new SaleStock();
            	ss.setSaleId(rs.getInt("sale_id"));
            	ss.setProductId(rs.getInt("product_id"));
            	ss.setProductName(rs.getString("product_name"));
            	ss.setQty(rs.getInt("qty"));
            	ss.setPrice(rs.getInt("price"));
            	ss.setDate(rs.getDate("date"));
            	saleStockList.add(ss);
            }
        }
        return saleStockList;
	}
}
