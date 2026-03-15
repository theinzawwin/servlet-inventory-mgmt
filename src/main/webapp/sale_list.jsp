<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<div>
	<h3>Sale Item List</h3>
	<a href="SaleServlet?action=new">Sale Item</a>
	<c:choose>
    <c:when test="${not empty saleStockList}">
	<table>
		<thead>
		<tr>
			<th>No.</th>
			<th>Item Name</th>
			<th>Sale Price</th>
			<th>Qty</th>
			<th>Total </th>
			<th>Date</th>
		</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${saleStockList}" var="ps" varStatus="row">
		<tr>
		<td>${row.count}</td>
			<td>${ps.productName }</td>
			<td>${ps.price }</td>
			<td>${ps.qty }</td>
			<td>${ps.total }</td>
			<td>${ps.date }</td>
		</tr>
		</c:forEach>
	</tbody>
	</table>
	</c:when>
    <c:otherwise>
        <p>No Sale Item found.</p>
    </c:otherwise>
</c:choose>	
</div>
<jsp:include page="footer.jsp"/>