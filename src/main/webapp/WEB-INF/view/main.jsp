<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="main">
	<ul id="products">
		<c:forEach items="${products}" var="product">
			<li class=product><a href="/MarketPlace/product/${product.id }"><img src="<c:url value="${product.url }" />" alt="" height="135" width="135" /></a><br></br>${product.title}</li>			
		</c:forEach>
	</ul>
</div>