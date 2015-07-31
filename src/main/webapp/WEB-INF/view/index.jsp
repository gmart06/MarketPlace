<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>MarketPlace</title>
		<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
	</head>
	<body>
		<%@include file='header.jsp'%>
		<%@include file='navbar.jsp'%>
		<table>
			<tr>
				<td><%@include file='menu.jsp'%></td>
				<td id="main"><%@include file='main.jsp'%></td>
			</tr>
		</table>
		<%@include file='footer.html'%>		
	</body>
</html>