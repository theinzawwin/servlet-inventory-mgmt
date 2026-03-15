<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<div>
	<h3>New Product</h3>
	<c:if test="${not empty errorMessage}">
    <div style="color:red">${errorMessage}</div>
	</c:if>
	<form action="ProductServlet" method="post">
	<table>
		<tr>
			<td>Category Name:</td>
			<td><select name="categoryId" onchange="handleSelectChange(this)">
				<option value="0">Select Category</option>
				<c:forEach var="c" items="${categoryList}">
					<option value="${c.categoryId}" ${param.categoryId == c.categoryId ? 'selected' : ''}>${c.categoryName }</option>
				</c:forEach>
			</select>
		</tr>
		
		<tr>
			<td>Product Name</td>
			<td><input type="text" name="productName"  value="${param.productName}"/></td>
		</tr>
		<tr>
			<td>Item Code</td>
			<td><input type="number" name="code"  value="${param.code}"/></td>
		</tr>
		
		<tr>
			<td></td>
			<td><input type="submit" value="Save" /></td>
		</tr>
	</table>
	</form>
</div>
<jsp:include page="footer.jsp"/>