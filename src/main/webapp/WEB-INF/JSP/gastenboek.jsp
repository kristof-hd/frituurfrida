<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri="http://www.springframework.org/tags"%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%> 
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Gastenboek' />
</head>
<body>
	<vdab:menu />
	<h1>Gastenboek</h1>
	<c:choose>
		<c:when test='${empty gastenBoekEntry}'>
			<c:url value='/gastenboek/toevoegen' var='url' />
			<a href='${url}'>Toevoegen</a>
		</c:when>
		<c:otherwise>
			<c:url value='/gastenboek' var='url' />
			<form:form action='${url}' method='post' modelAttribute='gastenBoekEntry'>
				<form:label path='naam'>Naam:<form:errors path='naam' /></form:label>
				<form:input path='naam' autofocus='autofocus' required='required' />
				<form:label path='bericht'>Bericht:<form:errors path='bericht' /></form:label>
				<form:textarea path='bericht' required='required' rows='5' cols='80' />
				<input type='submit' value='Toevoegen'>
			</form:form>
		</c:otherwise>
	</c:choose>
<%-- 	<c:if test='${not empty gastenboek}'> --%>
<!-- 		<dl> -->
<%-- 			<c:forEach var='entry' items='${gastenboek}'> --%>
<!-- 				<dt> -->
<%-- 					<spring:eval expression='entry.datumTijd' /> --%>
<%-- 					${entry.naam} --%>
<!-- 				</dt> -->
<%-- 				<dd>${entry.bericht}</dd> --%>
<%-- 			</c:forEach> --%>
<!-- 		</dl> -->
<%-- 	</c:if> --%>

	<c:if test='${not empty gastenboek}'>
		<c:url value='/gastenboek/verwijderen' var='url' />
		<form action='${url}' method='post'>
			<dl>
				<c:forEach var='entry' items='${gastenboek}'>
					<dt>
						<spring:eval expression='entry.datumTijd' />
						${entry.naam} <input type='checkbox' name='verwijderid' value='${entry.id}'>
					</dt>
					<dd>${entry.bericht}</dd>
				</c:forEach>
			</dl>
			<input type='submit' value='Verwijderen'>
		</form>
	</c:if>

</body>
</html>