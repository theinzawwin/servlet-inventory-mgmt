<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<div>
	<h3>Product List</h3>
	<a href="ProductServlet?action=new">New Product</a>
	<c:choose>
    <c:when test="${not empty productList}">
	<table>
		<thead>
		<tr>
			<th>No.</th>
			<th>Name</th>
			<th>Category</th>
			<th>Code</th>
		</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${productList}" var="b" varStatus="row">
		<tr>
		<td>${row.count}</td>
			<td>${b.productName }</td>
			<td>${b.categoryName }</td>
			<td>${b.code }</td>
		</tr>
		</c:forEach>
	</tbody>
	</table>
	</c:when>
    <c:otherwise>
        <p>No Product found.</p>
    </c:otherwise>
</c:choose>	
</div>
<jsp:include page="footer.jsp"/>