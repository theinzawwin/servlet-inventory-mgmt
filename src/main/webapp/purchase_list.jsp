<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<div>
	<h3>Purchase Item List</h3>
	<a href="PurchaseServlet?action=new">Purchase Item</a>
	<c:choose>
    <c:when test="${not empty purchaseStockList}">
	<table>
		<thead>
		<tr>
			<th>No.</th>
			<th>Item Name</th>
			<th>Purchase Price</th>
			<th>Qty</th>
			<th>Total </th>
		</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${purchaseStockList}" var="ps" varStatus="row">
		<tr>
		<td>${row.count}</td>
			<td>${ps.productName }</td>
			<td>${ps.price }</td>
			<td>${ps.qty }</td>
			<td>${ps.total }</td>
		</tr>
		</c:forEach>
	</tbody>
	</table>
	</c:when>
    <c:otherwise>
        <p>No Purchase Item found.</p>
    </c:otherwise>
</c:choose>	
</div>
<jsp:include page="footer.jsp"/>