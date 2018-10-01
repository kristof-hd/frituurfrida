<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%> 
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Snacks (alfabet)'/>
<style>
#alfabet {
	list-style-type: none;
}

#alfabet li {
	display: inline;
}
</style>
</head>
<body>
	<vdab:menu />
	<h1>Snacks (alfabet)</h1>
	<ul id='alfabet'>
		<c:forEach var='letter' items='${alfabet}'>
			<c:url value='/snacks' var='url'>
				<c:param name='beginletter' value='${letter}' />
			</c:url>
			<li><a href='${url}'>${letter}</a></li>
		</c:forEach>
	</ul>
	<c:if test='${not empty snacks}'>
		<ul>
			<c:forEach var='snack' items='${snacks}'>
<%-- 				<li>${snack.naam}</li> --%>
				<spring:url value='/snacks/{id}/wijzigen' var='url'>
					<spring:param name='id' value="${snack.id}"/> 
				</spring:url>
				<li><a href='${url}'>${snack.naam}</a></li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>