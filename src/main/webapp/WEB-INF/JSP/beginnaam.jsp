<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Snacks (begin naam)' />
</c:import>
<vdab:head title='Snacks (begin naam)' />
</head>
<body>
	<vdab:menu/>
	<h1>Snacks (begin naam)</h1>
	<c:url value='/snacks' var='url' />
	<form:form action='${url}' modelAttribute='beginNaamForm' method='get'>
		<form:label path='beginnaam'>Begin naam:
<form:errors path='beginnaam' />
		</form:label>
		<form:input path='beginnaam' required='required' autofocus='autofocus' />
		<input type='submit' value='Zoeken'>
		<form:errors />
	</form:form>
	<c:if test='${not empty snacks}'>
		<ul>
			<c:forEach items='${snacks}' var='snack'>
				<li>${snack.naam}</li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>