<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="Header.jsp"/>

	<div class="container">
	<h1>Add Supplier</h1>
	<f:form action="${contextRoot}/addSupplierProcess" method="post" modelAttribute="supplierObj">
		<div class="form-group">
      		<label for="supplierName">Supplier Name:</label>
      		<f:input type="text" class="form-control" id="supplierName" placeholder="Enter Supplier Name" path="supplierName"/>
    	    <f:errors style="color:red" path="supplierName"/>
    	</div>
    	<div class="form-group">
      		<label for="supplierAddress">Supplier Address:</label>
      		<f:input type="text" class="form-control" id="supplierAddress" placeholder="Enter Supplier Address" path="supplierAddress"/>
    	    <f:errors style="color:red" path="supplierAddress"/>
    	</div>
    	<button type="submit" class="btn btn-default">Add Supplier</button>
    </f:form>	
	</div>
<div style="margin-top:300px">
<jsp:include page="Footer.jsp"/>
</div>