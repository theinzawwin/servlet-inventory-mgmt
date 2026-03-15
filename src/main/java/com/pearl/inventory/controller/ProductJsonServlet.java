package com.pearl.inventory.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pearl.inventory.dao.ProductDao;
import com.pearl.inventory.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Servlet implementation class ProductJsonServlet
 */
@WebServlet("/ProductJsonServlet")
public class ProductJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final ProductDao productDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductJsonServlet() {
        super();
        // TODO Auto-generated constructor stub
        productDao= new ProductDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer categoryId= Integer.parseInt(request.getParameter("categoryId")); 
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		List<Product> productList;
		try {
			productList = productDao.getProductByCategory(categoryId);
			ObjectMapper mapper = new ObjectMapper();
	        String json = mapper.writeValueAsString(productList);

	        response.getWriter().write(json);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
