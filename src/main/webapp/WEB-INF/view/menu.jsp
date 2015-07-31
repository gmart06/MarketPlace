<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="menu"> 
	<ul id="categories">
		<c:forEach items="${categoriesName1}" var="categoriesName1">
			<li><a href="/MarketPlace/category/X">${categoriesName1}</a></li>			
		</c:forEach>
	</ul>
</div>