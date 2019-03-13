<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Header.jsp"/>


<div class="container">
	<fieldset>
		<legend>SignUp Form</legend>
		<f:form action="${contextRoot}/register" method="post" modelAttribute="userObj">
       	
       	<div class="form-group">
    	<label for="email">Email : </label>
    	<f:input type="email" class="form-control" path="email"/>
    	<f:errors style="color:red" path="email"/>
		</div>
  
  		<div class="form-group">
  		<label for="fName">First Name:</label>
    	<f:input type="text" class="form-control" path="fName"/>
    	<f:errors style="color:red" path="fName"/>
	  	</div>
	  	
	  	<div class="form-group">
  		<label for="lName">Last Name:</label>
    	<f:input type="text" class="form-control" path="lName"/>
    	<f:errors style="color:red" path="lName"/>
	  	</div>
  	
  		<div class="form-group">
  		<label for="password">Password:</label>
    	<f:input type="password" class="form-control" path="password"/>
    	<f:errors style="color:red" path="password"/>
    	</div>
    	
    	<div class="form-group">
  		<label for="confirmPassword">Confirm Password:</label>
    	<f:input type="password" class="form-control" path="confirmPassword"/>
    	<f:errors style="color:red" path="confirmPassword"/>
    	</div>
	  	
	  	<div class="form-group">
  		<label for="phone">Contact Number:</label>
    	<f:input type="text" class="form-control" path="phone"/>
    	<f:errors style="color:red" path="phone"/>
    	</div>
	  	
	  		<button type="submit" class="btn btn-primary">Register User</button>
	  </f:form>	
	</fieldset>
</div>

<br/><br/>
<jsp:include page="Footer.jsp"/>