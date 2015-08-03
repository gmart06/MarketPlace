<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="main">

	<ul id="products">
	<c:forEach items="${ products }" var="prod">

		<c:if test="${prod.productDetails.size() > 0 }">
		
		<li class=product>
		<a href="/MarketPlace/product/${prod.id }"><img src="<c:url value="${prod.productDetails.iterator().next().image_url}" />" alt="${prod.title}" title="${prod.title}" height="135" width="135" /></a>
		<div class="price"> ${prod.productDetails.iterator().next().price} €</div>

		<div class="title">${prod.title}</div>
		<div class="add">
			<form:form commandName="addBasket"> 
			
			
	            <input TYPE="SUBMIT" NAME="submit" VALUE="Ajouter au panier">
	            
	        </form:form>
        </div>
		</li>
	
		</c:if>	
		
	</c:forEach>
	</ul>
</div>
<!-- 
<li class=product id="product1"><img src="<c:url value="${ prod.productDetails[0].image_url }" />" alt="" height="135" width="135" /><p> ${ prod.productDetails[0][0].sku }</li>
 -->