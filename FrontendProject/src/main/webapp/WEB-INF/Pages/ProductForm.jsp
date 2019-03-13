<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Header.jsp"/>


<div class="container">
	<fieldset>
		<legend>Add Product</legend>
		<f:form action="${contextRoot}/addProductProcess" method="post" modelAttribute="productObj" enctype="multipart/form-data">
       	
       	<div class="form-group">
    	<label for="productName">Product Name : </label>
    	<f:input type="text" class="form-control" path="productName"/>
		<f:errors style="color:red" path="productName"/>
		</div>
  
  		<div class="form-group">
  		<label for="price">Price:</label>
    	<f:input type="number" class="form-control" path="price"/>
	  	<f:errors style="color:red" path="price"/>
	  	</div>
  	
  		<div class="form-group">
  		<label for="quantity">Quantity:</label>
    	<f:input type="number" class="form-control" path="quantity"/>
	  	<f:errors style="color:red" path="quantity"/>
	  	</div>
	  	
	  	<div class="form-group">
  		<label for="description">Description:</label>
    	<f:input type="text" class="form-control" path="description"/>
	  	<f:errors style="color:red" path="description"/>
	  	</div>
	  	
	  	<div class="form-group">
  		<label for="categoryId">CategoryId:</label>
    	<f:select path="categoryId" class="form-control">
    		<f:option value="0">-----Select Category-----</f:option>
    		<c:forEach items="${categoryList}" var="categoryObj">
    			<f:option value="${categoryObj.categoryId}">${categoryObj.categoryName}</f:option>
    		</c:forEach>	
    	</f:select>
	  	</div>
	  	
	  	
	  	<div class="form-group">
  		<label for="supplierId">Supplier Id:</label>
    	<f:select path="supplierId" class="form-control">
    		<f:option value="0">-----Select Supplier-----</f:option>
    		<c:forEach items="${supplierList}" var="supplierObj">
    			<f:option value="${supplierObj.supplierId}">${supplierObj.supplierName}</f:option>
    		</c:forEach>	
    	</f:select>
	  	</div>
	  	
	  	<div class="form-group">
  		<label for="pimage1">Upload Image:</label>
    	<f:input type="file" class="form-control" path="pimage1"/>
	  	</div>
  
		<button type="submit" class="btn btn-primary">Add Product</button>
	  </f:form>	
	</fieldset>
</div>

<br/><br/>
<jsp:include page="Footer.jsp"/>