package com.pearl.inventory.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pearl.inventory.model.Category;
import com.pearl.inventory.model.Product;
import com.pearl.inventory.model.SaleProductItem;

public class ProductDao {

	private static final String URL = "jdbc:mysql://localhost:3306/pearl_inventory";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
	public List<Category> getAllCategory() throws ClassNotFoundException, SQLException{
		List<Category> list = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM category")) {
            while (rs.next()) {
                list.add(new Category(
                    rs.getInt("category_id"),
                    rs.getString("category_name")
               
                ));
            }
        }
        return list;
	}
	
	public boolean saveCategory(Category c) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO category (category_name, status) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getCategoryName());
            stmt.setString(2, "Y");
           
            stmt.executeUpdate();
        }
		return true;
	}
	public boolean updateCategory(Category c) {
		return true;
	}
	
	public List<Product> getAllProducts() throws ClassNotFoundException, SQLException{
		List<Product> productList = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.*,c.category_id,c.category_name FROM pearl_inventory.product p left join pearl_inventory.category c on p.category_id=c.category_id")) {
            while (rs.next()) {
            	productList.add(new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getInt("category_id"),
                    rs.getString("category_name"),
                    rs.getString("code")
               
                ));
            }
        }
        return productList;
	}
	
	public boolean saveProduct(Product p) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO product (product_name, category_id,code) VALUES (?, ?,?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getProductName());
            stmt.setInt(2, p.getCategoryId());
            stmt.setString(3, p.getCode());
           
            stmt.executeUpdate();
        }
		return true;
	}
	
	public List<Product> getProductByCategory(Integer categoryId) throws ClassNotFoundException, SQLException{
		String sql="SELECT p.*,c.category_id,c.category_name FROM pearl_inventory.product p left join pearl_inventory.category c on p.category_id=c.category_id where p.category_id=?";
		List<Product> productList = new ArrayList<>();
        try (Connection conn = getConnection();
        		
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
        		stmt.setInt(1, categoryId);
             ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	productList.add(new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getInt("category_id"),
                    rs.getString("category_name"),
                    rs.getString("code")
               
                ));
            }
        }
        return productList;
	}
	
	public List<SaleProductItem> getSaleProductByCategory(Integer categoryId) throws ClassNotFoundException, SQLException{
		String sql="select s.stock_id,s.sale_price,s.qty,p.product_id,p.product_name,p.code from stock s join product p on s.product_id=p.product_id where s.qty>0 and p.category_id=?";
		List<SaleProductItem> saleItemList = new ArrayList<>();
        try (Connection conn = getConnection();
        		
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
        		stmt.setInt(1, categoryId);
             ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	SaleProductItem saleItem = new SaleProductItem();
            	saleItem.setProductId(rs.getInt("product_id"));
            	saleItem.setProductName(rs.getString("product_name"));
            	saleItem.setCode(rs.getString("code"));
            	saleItem.setSalePrice(rs.getInt("sale_price"));
            	saleItem.setQty(rs.getInt("qty"));
            	saleItemList.add(saleItem);
            }
        }
        return saleItemList;
	}
	
}
