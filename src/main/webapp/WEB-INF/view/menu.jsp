<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="menu">
 
	<ul id="test">
		<c:forEach items="${categories}" var="category">
			<li>${category.name}</li>
		</c:forEach>
	</ul>


	
</div>