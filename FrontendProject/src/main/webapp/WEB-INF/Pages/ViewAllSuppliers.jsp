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
				<th>Supplier Id</th>
				<th>Supplier Name</th>
				<th>Supplier Address</th>
				<th colspan="2">Operations</th>
			</tr>
			
			<c:forEach items="${supplierList}" var="supplier">
			<tr>
				<td>${supplier.supplierId}</td>
				<td>${supplier.supplierName}</td>
				<td>${supplier.supplierAddress}</td>
				<td><a href="${contextRoot}/updateSupplier/${supplier.supplierId}">Update</a></td>
				<td><a href="${contextRoot}/deleteSupplier/${supplier.supplierId}">Delete</a></td>
			</tr>
			</c:forEach>
			
		</table>
	</div>
<div style="margin-top:300px">
<jsp:include page="Footer.jsp"/>
</div>