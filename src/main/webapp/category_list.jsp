<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<div>
	<h3>Category List</h3>
	<a href="CategoryServlet?action=new">New Category</a>
	<c:choose>
    <c:when test="${not empty categoryList}">
	<table>
		<thead>
		<tr>
			<th>No.</th>
			<th>Name</th>
		</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${categoryList}" var="b" varStatus="row">
		<tr>
		<td>${row.count}</td>
			<td>${b.categoryName }</td>
			
		</tr>
		</c:forEach>
	</tbody>
	</table>
	</c:when>
    <c:otherwise>
        <p>No Category found.</p>
    </c:otherwise>
</c:choose>	
</div>
<jsp:include page="footer.jsp"/>