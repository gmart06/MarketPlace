<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<title>MENU</title>
</head>
<body>
	<h2> Liste des categories</h2>

<ul id="dropdown">
				<c:forEach items="${ categories }" var="cat1">
				<li> <a href="/list/?id=${ cat1.id }/"> ${ cat1.name }</a>
					<ul>
					<c:forEach items="${ category2 }" var="cat2" end="9">
						<li>
							<a href="/cat${ cat2.level_hierarchy }?id=${ cat2.id }/"> ${ cat2.name }</a>
							<ul>
					<c:forEach items="${ category3 }" var="cat3" end="9">
						<li>
							<a href="/cat${ cat3.level_hierarchy }?id=${ cat3.id }/"> ${ cat3.name }</a>
						</li>
						</c:forEach>
					</ul>
						</li>
						</c:forEach>
					</ul>
				</li>
				</c:forEach>
</ul>
</body>
</html>
