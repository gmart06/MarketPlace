<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="main">

	<ul id="products">
	<c:forEach items="${ products }" var="prod">



	<c:out value="${prod.productDetails[0] }"/>
	
		<li class=product id="product1"><img src="<c:url value="${ prod.productDetails[0][0].image_url }" />" alt="" height="135" width="135" /><p> ${ prod.productDetails[0][0].sku }</li>
			</c:forEach>
	</ul>
	
</div>