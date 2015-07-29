<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>MarcketPlace</title>
	</head> 
	<body>
		<h2>Add a catalog</h2>
		<form:form method="post" modelAttribute="catalogForm" enctype="multipart/form-data">
			XML : <input name="catalogFile" type="file" /><input type="submit" value="Send" /><span class="errorFileType" style="color:red"> ${ errorFileType }</span>		
		</form:form>
		<br />
		<div class="success">${ success }</div>
		<div class="errorXML">
			<h4>${ errorXMLTitle }</h4>
			<ul>
				<c:forEach items="${ errorXML }" var="e">
					<li>At line ${ e.line } : ${ e.message }</li>
				</c:forEach>
			</ul>
		</div>
	</body>
</html>
