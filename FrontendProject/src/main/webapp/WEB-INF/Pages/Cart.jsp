<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!------ Include the above in your HEAD tag ---------->


<jsp:include page="Header.jsp"/>
<div class="container">

     <c:if test="${not empty addressObj}">
       <tr>Delivered here </tr>
       <td>${addressObj.addressLine1} ${addressObj.addressLine2}</td>
       <td>${addressObj.city} ${addressObj.state} ${addressObj.pinCode}</td>
     </c:if> 

	<c:choose>
		<c:when test="${not empty msg}">
			${msg}
		</c:when>
		<c:otherwise>
		
    <div class="row">
        <div class="col-sm-12 col-md-10 col-md-offset-1">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th class="text-center">Price</th>
                        <th class="text-center">Total</th>
                        <th> </th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${cartItems}" var="item">
                    <tr>
                        <td class="col-sm-8 col-md-6">
                        <div class="media">
                            <a class="thumbnail pull-left" href="#"> <img class="media-object" src="${images}/${item.product.imgName1}" style="width: 72px; height: 72px;"> </a>
                            <div class="media-body">
                                <h4 class="media-heading"><a href="#">${item.product.productName}</a></h4>
                                
                                
                                <span>Status: </span><span class="text-success">
                                <strong>
                                
                                <c:choose>
                                 <c:when test="${item.product.quantity >item.quantity }">
                                In Stock
                                </c:when>
                                <c:otherwise>Out Of Stock</c:otherwise>
                                </c:choose>
                                </strong></span></div></div>
                                
                              
                        </td>
                        <td class="col-sm-1 col-md-1" style="text-align: center">
                        ${item.quantity}
                        </td>
                        <td class="col-sm-1 col-md-1 text-center"><strong>${item.price}</strong></td>
                        <td class="col-sm-1 col-md-1 text-center"><strong>${item.quantity*item.price}</strong></td>
                        <td class="col-sm-1 col-md-1">
                        
                        <a class="btn btn-primary" href="${contextRoot}/increaseQuantity/${item.itemId}/${item.product.productId}">+</a>
                       	<a class="btn btn-primary" href="${contextRoot}/decreaseQuantity/${item.itemId}">-</a>
                        
                        
                        </td>
                    </tr>
                    </c:forEach>
                    <tr>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        <td><h5>Subtotal</h5></td>
                        <td class="text-right"><h5><strong>${totalCost}</strong></h5></td>
                    </tr>
                    <tr>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        <td><h5>Estimated shipping</h5></td>
                        <td class="text-right"><h5><strong>90</strong></h5></td>
                    </tr>
                    <tr>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        <td><h3>Total</h3></td>
                        <td class="text-right"><h3><strong>${totalCost+90}</strong></h3></td>
                    </tr>
                    
                     <c:if test="${not empty addressObj}">
                    <tr>
                    	<td>Address for Delivery : </td>
                    	<td></td>
                    	<td>${addressObj.addressLine1} ${addressObj.addressLine2}</td>
                    	<td>${addressObj.city} ${addressObj.state} ${addressObj.pinCode}</td>
                    </tr>
                    </c:if>
                    
                    
                    <tr>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        
                       <!--  <button type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                        </button></td> -->
                        <td>
                       
                            
                            
                            
                            <c:choose>
                            <c:when test="${not empty addressObj}" >
                            <td>
                            <a href="${contextRoot}/makePayment" class="btn btn-success">
                            Make Payment <span class="glyphicon glyphicon-play"></span></td>
                            
                            </a>
                            </c:when>
                            <c:otherwise>
                             <a href="${contextRoot}/getAddressPage" class="btn btn-success">
                            Checkout <span class="glyphicon glyphicon-play"></span></td>
                            </c:otherwise>
                            </c:choose>
                        </a></td>
                    </tr>
                </tbody>
            </table>
        </div>
        </c:otherwise>
	</c:choose>
    </div>
 </div>
<jsp:include page="Footer.jsp"/>

