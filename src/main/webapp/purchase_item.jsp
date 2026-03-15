<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<script>
function handleSelectChange(selectElement) {
    var selectedValue = selectElement.value;
    alert("You selected: " + selectedValue);
    fetch('ProductJsonServlet?categoryId=' + encodeURIComponent(selectedValue))
    .then(response => response.json())
    .then(data => {
    	console.log("Server Response:", data);
        const productSelect = document.getElementById("productId");
        productSelect.innerHTML = ""; // clear old options

        // Add a default option
        const defaultOption = document.createElement("option");
        defaultOption.text = "-- Select Product --";
        defaultOption.value = "";
        productSelect.add(defaultOption);

        data.forEach(product => {
            const option = document.createElement("option");
            option.value = product.productId;
            option.text = product.productName;
            productSelect.add(option);
        });
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

function updateTotal() {
	console.log("In Updatetotal method");
    const qty = parseInt(document.getElementById("qty").value) || 0;
    const price = parseFloat(document.getElementById("price").value) || 0;
    const total = qty * price;
    document.getElementById("total").value = total.toFixed(2);
  }

</script>
<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />
<div>
	<h3>New Purchase</h3>
	<form action="PurchaseServlet" method="post">
	<table>
		<tr>
			<td>Category Name:</td>
			<td><select name="categoryId" onchange="handleSelectChange(this)">
				<option value="0">Select Category</option>
				<c:forEach var="c" items="${categoryList}">
					<option value="${c.categoryId}">${c.categoryName }</option>
				</c:forEach>
			</select>
		</tr>
		<tr>
			<td>Item</td>
			<td>
				<select name="productId" id="productId">
					<option value="">--Select Item--</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Price</td>
			<td><input type="number" name="price" id="price" oninput="updateTotal()" /></td>
		</tr>
		<tr>
			<td>Qty</td>
			<td><input type="number" name="qty" id="qty" oninput="updateTotal()"/></td>
		</tr>
		<tr>
			<td>Total</td>
			<td><input type="number" id="total" readonly /></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Save" /></td>
		</tr>
	</table>
	</form>
</div>
<jsp:include page="footer.jsp"/>