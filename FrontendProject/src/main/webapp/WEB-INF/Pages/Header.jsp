
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

 <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>   
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" scope="application"/>
<spring:url value="/resources/images" var="images" scope="session"/>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Brand Yuva</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="<c:url value="/" />">Brand Yuva</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="<c:url value="/" />">Home</a></li>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Shop By Category
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
           <c:forEach items="${sessionScope.categoryList}" var="categoryObj">
          <li><a href="${contextRoot}/getAllProductsByCategory/${categoryObj.categoryId}">${categoryObj.categoryName}</a></li>
          </c:forEach>
        </ul>
      </li>
      
      <li><a href="#">About Us</a></li>
      <li><a href="#">Contact Us</a></li>
     
      <sec:authorize access="hasAuthority('Admin')">  
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Admin
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="${contextRoot}/addCategory">Add Category</a></li>
          <li><a href="${contextRoot}/viewAllCategories">View All Categories</a></li>
          <li><a href="${contextRoot}/addSupplier">Add Supplier</a></li>
          <li><a href="${contextRoot}/viewAllSuppliers">View All Suppliers</a></li>
          <li><a href="${contextRoot}/addProduct">Add Product</a></li>
          <li><a href="${contextRoot}/viewAllProducts">View All Products</a></li>
        </ul>
      </li>
       
      </sec:authorize>
      
      <sec:authorize access="hasAuthority('User')">
      <li><a href="${contextRoot}/viewCart"><span class="glyphicon glyphicon-shopping-cart"></span>View Cart ${itemsCount}</a></li>
      </sec:authorize>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    	
       <sec:authorize access="isAnonymous()">
      	<li><a href="${contextRoot}/registerUser"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      	<li><a href="${contextRoot}/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
       </sec:authorize>
       
       <sec:authorize access="isAuthenticated()">
      	<li style="color:white">Welcome : ${sessionScope.userObj.fName} ${sessionScope.userObj.lName}</li>
      	<li><a href="${contextRoot}/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
       </sec:authorize> 
    
    
    </ul>
  </div>
</nav>