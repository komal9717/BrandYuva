<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="Header.jsp"/>

	<div class="container">
	<h1>Update Category</h1>
	<f:form action="${contextRoot}/updateCategoryProcess" method="post" modelAttribute="categoryObj">
		<div class="form-group">
      		<label for="categoryId">Category Id:</label>
      		<f:input type="text" class="form-control" id="categoryName" placeholder="Enter Category Id" path="categoryId" readonly="true"/>
    	</div>
		<div class="form-group">
      		<label for="categoryName">Category Name:</label>
      		<f:input type="text" class="form-control" id="categoryName" placeholder="Enter Category Name" path="categoryName"/>
    	    <f:errors path="categoryName" style="color:red"/>
    	</div>
    	<div class="form-group">
      		<label for="categoryDesc">Category Description:</label>
      		<f:input type="text" class="form-control" id="categorydesc" placeholder="Enter Category Description" path="categoryDesc"/>
    	    <f:errors path="categoryDesc" style="color:red"/>
    	</div>
    	<button type="submit" class="btn btn-default">Update Category</button>
    </f:form>
	</div>
<div style="margin-top:300px">
<jsp:include page="Footer.jsp"/>
</div>