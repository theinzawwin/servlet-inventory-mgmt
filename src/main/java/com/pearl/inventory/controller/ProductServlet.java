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
import com.pearl.inventory.model.Category;
import com.pearl.inventory.model.Product;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final ProductDao productDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
        productDao= new ProductDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action= request.getParameter("action");
		try {
			if(action!=null && action.equals("new")) {
				List<Category> categoryList = productDao.getAllCategory();
				request.setAttribute("categoryList", categoryList);
				request.getRequestDispatcher("new_product.jsp").forward(request, response);
				
			}else {
				showAllProduct(request,response);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
		String productName = request.getParameter("productName");
		String code = request.getParameter("code");
		Integer categoryId = 0;
		String errorMessage=null;
		if(productName==null || productName.trim().isEmpty() ) {
			errorMessage = "Product Name is required";
		}else if(code==null ||code.trim().isEmpty()) {
			errorMessage = "Code is required";
		}else if(request.getParameter("categoryId")==null || Integer.parseInt(request.getParameter("categoryId"))==0) {
			errorMessage = "Category is required";
		}else {
			categoryId=Integer.parseInt(request.getParameter("categoryId"));
			Product product = new Product();
			product.setCategoryId(categoryId);
			product.setProductName(productName);
			product.setCode(code);
			try {
				boolean result = productDao.saveProduct(product);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(errorMessage!=null) {
			request.setAttribute("errorMessage", errorMessage);
			List<Category> categoryList;
			try {
				categoryList = productDao.getAllCategory();
				request.setAttribute("categoryList", categoryList);
	            request.getRequestDispatcher("new_product.jsp").forward(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			//response.sendRedirect("save_success.jsp");
			try {
				showAllProduct(request,response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	protected void showAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
		List<Product> productList = productDao.getAllProducts();
		request.setAttribute("productList", productList);
		request.getRequestDispatcher("product_list.jsp").forward(request, response);
		
		
	}
	

}
