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

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final ProductDao categoryDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryServlet() {
    	
    	super();
    	this.categoryDao= new ProductDao();
    }

   
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action= request.getParameter("action");
		if(action!=null && action.equals("new")) {
			request.getRequestDispatcher("category.jsp").forward(request,response);
		}else {
			showCategory(request,response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		saveCategory(request, response);
		showCategory(request, response);
	}
	
	
	protected void saveCategory(HttpServletRequest request, HttpServletResponse response) {
		String categoryName = request.getParameter("categoryName");
		String errorMessage = null;
		try {
			if (categoryName == null || categoryName.trim().isEmpty()) {
	            errorMessage = "Category name is required.";
	        }else {
	        	Category cat = new Category(0,categoryName);
				categoryDao.saveCategory(cat);
	        }
			if (errorMessage != null) {
	            request.setAttribute("errorMessage", errorMessage);
	            request.getRequestDispatcher("category.jsp").forward(request, response);
	        } else {
	            response.sendRedirect("save_success.jsp"); // redirect to success page
	        }
		}catch(Exception ex) {
			
		}
	}
	protected void showCategory(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Category> categoryList = categoryDao.getAllCategory();
			try {
				request.setAttribute("categoryList", categoryList);
				request.getRequestDispatcher("category_list.jsp").forward(request,response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
