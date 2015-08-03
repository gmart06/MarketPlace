<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="main">

	<ul id="products">
	<c:forEach items="${ products }" var="prod">
		<li class=product id="product1"><img src="<c:url value="${ prod.url }" />" alt="" height="135" width="135" /><p> ${ prod.sku }</li>
			</c:forEach>
	</ul>
	
</div>