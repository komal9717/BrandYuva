<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="Header.jsp"/>

	<div class="container">

		<c:choose>
			<c:when test="${not empty addressList}">
				<table class="table table-hover">
			<tr>
				<th>Address Line 1</th>
				<th>Address Line 2</th>
				<th>City</th>
				<th>State</th>
				<th>Pincode</th>
				<th colspan="2">Operations</th>
			</tr>
			
			<c:forEach items="${addressList}" var="addr">
			<tr>
				<td>${addr.addressLine1}</td>
				<td>${addr.addressLine2}</td>
				<td>${addr.city}</td>
				<td>${addr.state}</td>
				<td>${addr.pinCode}</td>
			    <td><a href="${contextRoot}/updateAddress/${addr.addressId}" class="btn btn-info" style="color:black">Update</a></td>
				<td><a href="${contextRoot}/deleteAddress/${addr.addressId}" class="btn btn-info" style="color:black">Delete</a></td> 
				
				
				<th>
					<a href="${contextRoot}/confirmationPage?address=${addr.addressId}" class="btn btn-info"  style="color:black">
						Deliver to this Address
					</a>
				</th> 
				
			</tr>
			</c:forEach>
			
		</table>
	
			</c:when>
			<c:otherwise>
				No existing address available
			</c:otherwise>
		</c:choose>	
	
	
	<h1>Add New Address</h1>
	<f:form action="${contextRoot}/addAddress" method="post" modelAttribute="addressObj">
		<div class="form-group">
      		<label for="addressLine1">Address Line 1:</label>
      		<f:input type="text" class="form-control" id="addressLine1" placeholder="Enter Address Line1" path="addressLine1"/>
      		<f:errors style="color:red" path="addressLine1"/>	
    	</div>
    	
    	<div class="form-group">
      		<label for="addressLine2">Address Line 2:</label>
      		<f:input type="text" class="form-control" id="addressLine2" placeholder="Enter Address Line2" path="addressLine2"/>
      		<f:errors style="color:red" path="addressLine2"/>	
    	</div>
    	
    	<div class="form-group">
      		<label for="city">City:</label>
      		<f:input type="text" class="form-control" id="city" placeholder="Enter City" path="city"/>
      		<f:errors style="color:red" path="city"/>	
    	</div>
    	
    	<div class="form-group">
      		<label for="state">State:</label>
      		<f:input type="text" class="form-control" id="state" placeholder="Enter State" path="state"/>
      		<f:errors style="color:red" path="state"/>	
    	</div>
    	
    	<div class="form-group">
      		<label for="pincode">Pin Code:</label>
      		<f:input type="text" class="form-control" id="pincode" placeholder="Enter Pin Code" path="pinCode"/>
      		<f:errors style="color:red" path="pinCode"/>	
    	</div>
    	
    	<button type="submit" class="btn btn-info" style="color:black">Add Address</button>
    </f:form>	
	</div>
<div style="margin-top:300px">
<jsp:include page="Footer.jsp"/>
</div>