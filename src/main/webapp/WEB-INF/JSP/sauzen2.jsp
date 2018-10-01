<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Sauzen' />
</c:import>
</head>
<body>
	<h1>Sauzen</h1>
	<c:forEach var='saus' items='${sauzen}'>
		<h2>${saus.naam}</h2>
		<img src='images/${saus.naam}.png' alt='${saus.naam}'> ingrediënten:
		<c:forEach var='ingredient' items='${saus.ingredienten}' varStatus='status'>
			${ingredient}<c:if test='${not status.last}'>, </c:if>
		</c:forEach>
	</c:forEach>
</body>
</html>