<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="main">
	<ul id="products">
		<c:forEach items="${products}" var="product">
		<a href="/MarketPlace/product/${product.id }">
		
		<li class=product>
			<img src="<c:url value="${product.productDetails[0].image_url }" />" alt="" height="135" width="135" />
			
			<br></br><br></br>${product.title}</li>	</a>		
		</c:forEach>
	</ul>
</div>