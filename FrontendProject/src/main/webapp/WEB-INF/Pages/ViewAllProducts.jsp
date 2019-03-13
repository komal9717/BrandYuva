<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Header.jsp"/>
		<div class="container">
		
		<c:if test="${not empty msg}">
		<div class="alert alert-success">	
		${msg}
		</div>
		</c:if>	
		
		<table class="table table-hover">
			<tr>
				<th>Product Id</th>
				<th>Product Name</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Description</th>
				<th>Category Id</th>
				<th>Image</th>
				<th colspan="2">Operations</th>
			</tr>
			
			<c:forEach items="${productList}" var="product">
			<tr>
				<td>${product.productId}</td>
				<td>${product.productName}</td>
				<td>${product.price}</td>
				<td>${product.quantity}</td>
				<td>${product.description}</td>
				<td>${product.categoryId}</td>
				<td><img src="${images}/${product.imgName1}" style="height:320px;width:240px"/></td>
				<td><a href="${contextRoot}/updateProduct/${product.productId}">Update</a></td>
				<td><a href="${contextRoot}/deleteProduct/${product.productId}">Delete</a></td>
			</tr>
			</c:forEach>
			
		</table>
	</div>
<div style="margin-top:300px">
<jsp:include page="Footer.jsp"/>
</div>