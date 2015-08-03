<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>MarketPlace</title>
		<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
		
<link href="<c:url value="/resources/script/css/styles.css" />" rel="stylesheet">
<link href="<c:url value="/resources/script/css/juizDropDownMenu.css" />" rel="stylesheet">
<script type="text/javascript" 
src="/MarketPlace/resources/script/js/jquery.min.js">
</script>
<script type="text/javascript" src="/MarketPlace/resources/script/js/jquery-1.7.2.min.js"></script>	
		<script type="text/javascript" src="/MarketPlace/resources/script/js/juizDropDownMenu-2.0.0.min.js"></script>
		<script type="text/javascript">
		$(function(){
			$("#dropdown").juizDropDownMenu({
				'showEffect' : 'fade',
				'hideEffect' : 'slide'
			});
		});
		</script>
	</head>
	<body>
		<%@include file='header.jsp'%>

		<ul id="dropdown">
				<c:forEach items="${ categories }" var="cat1">
				<li> <a href="/MarketPlace/${ cat1.id }"> ${ cat1.name }</a>
					<ul>
					<c:forEach items="${ category2 }" var="cat2" end="9">
						<li>
							<a href="/MarketPlace/${ cat2.id }"> ${ cat2.name }</a>
							<ul>
					<c:forEach items="${ category3 }" var="cat3" end="9">
						<li>
							<a href="/MarketPlace/${ cat3.id }"> ${ cat3.name }</a>
						</li>
						</c:forEach>
					</ul>
						</li>
						</c:forEach>
					</ul>
				</li>
				</c:forEach>
		</ul>


		<table>
			<tr>
				<td><%@include file='menu.jsp'%></td>
				<td id="main"><%@include file='main.jsp'%></td>
			</tr>
		</table>
		<%@include file='footer.html'%>		
	</body>
</html>