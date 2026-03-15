package com.pearl.inventory.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pearl.inventory.dao.InventoryDao;
import com.pearl.inventory.dao.ProductDao;
import com.pearl.inventory.model.Category;
import com.pearl.inventory.model.PurchaseStock;
import com.pearl.inventory.model.SaleStock;
import com.pearl.inventory.model.Stock;

/**
 * Servlet implementation class SaleServlet
 */
@WebServlet("/SaleServlet")
public class SaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final ProductDao productDao;
	
	private final InventoryDao inventoryDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleServlet() {
        super();
        productDao = new ProductDao();
        inventoryDao = new InventoryDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if(action!=null && action.equals("new")) {
			List<Category> categoryList;
			try {
				categoryList = productDao.getAllCategory();
				request.setAttribute("categoryList", categoryList);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher rd= request.getRequestDispatcher("sale_item.jsp");
			rd.forward(request, response);
		}else {
			try {
				request.setAttribute("saleStockList", inventoryDao.getAllSaleList());
				RequestDispatcher rd = request.getRequestDispatcher("sale_list.jsp");
				rd.forward(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer productId = Integer.parseInt(request.getParameter("productId"));
		Integer price = Integer.parseInt(request.getParameter("price"));
		Integer qty = Integer.parseInt(request.getParameter("qty"));
		String errorMessage = null;
		if(request.getParameter("productId")==null || productId==0) {
			errorMessage = "Product is required";
		}else if(request.getParameter("qty")==null || qty==0) {
			errorMessage = "Sale Quantity is required";
		}else if(request.getParameter("price")==null || qty==0) {
			errorMessage = "Price is required";
		}else {
			
			try {
				SaleStock ps = new SaleStock();
				ps.setDate(new Date());
				ps.setProductId(productId);
				ps.setQty(qty);
				ps.setPrice(price);
				boolean purchaseStatus=inventoryDao.saveSaleStock(ps);
				if(purchaseStatus==true) {
					Stock stockProduct = inventoryDao.getStockByProductId(productId);
						stockProduct.setPurchasePrice(price);
						int stockQty= stockProduct.getQty()-qty;
						stockProduct.setQty(stockQty);
						inventoryDao.updateStock(stockProduct);
					
					response.sendRedirect("save_success.jsp");
				}
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
	            request.getRequestDispatcher("sale_item.jsp").forward(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
