<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="Header.jsp"/>

	<div class="container">
	<h1>Update Supplier</h1>
	<f:form action="${contextRoot}/updateSupplierProcess" method="post" modelAttribute="supplierObj">
		<div class="form-group">
      		<label for="supplierId">Supplier Id:</label>
      		<f:input type="text" class="form-control" id="supplierId" placeholder="Enter Supplier Id" path="supplierId" readonly="true"/>
    	</div>
		<div class="form-group">
      		<label for="supplierName">Supplier Name:</label>
      		<f:input type="text" class="form-control" id="supplierName" placeholder="Enter Supplier Name" path="supplierName"/>
    	    <f:errors style="color:red"  path="supplierName"/>
    	</div>
    	<div class="form-group">
      		<label for="supplierAddress">Supplier Address:</label>
      		<f:input type="text" class="form-control" id="supplierAdd" placeholder="Enter Supplier Address" path="supplierAddress"/>
    	     <f:errors style="color:red"  path="supplierAddress"/>
    	</div>
    	<button type="submit" class="btn btn-default">Update Supplier</button>
    </f:form>
	</div>
<div style="margin-top:300px">
<jsp:include page="Footer.jsp"/>
</div>