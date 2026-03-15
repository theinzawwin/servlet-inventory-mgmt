<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<div>
	<h3>New Category</h3>
	<c:if test="${not empty errorMessage}">
    <div style="color:red">${errorMessage}</div>
	</c:if>
	<form action="CategoryServlet" method="post">
	<table>
		<tr>
			<td>Category Name:</td>
			<td><input type="text" name="categoryName" />
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Save" /></td>
		</tr>
	</table>
	</form>
</div>
<jsp:include page="footer.jsp"/>