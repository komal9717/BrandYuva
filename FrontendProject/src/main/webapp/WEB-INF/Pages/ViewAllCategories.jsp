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
				<th>Category Id</th>
				<th>Category Name</th>
				<th>Category Desc</th>
				<th colspan="2">Operations</th>
			</tr>
			
			<c:forEach items="${categoryList}" var="category">
			<tr>
				<td>${category.categoryId}</td>
				<td>${category.categoryName}</td>
				<td>${category.categoryDesc}</td>
				<td><a href="${contextRoot}/updateCategory/${category.categoryId}">Update</a></td>
				<td><a href="${contextRoot}/deleteCategory/${category.categoryId}">Delete</a></td>
			</tr>
			</c:forEach>
			
		</table>
	</div>
<div style="margin-top:300px">
<jsp:include page="Footer.jsp"/>
</div>