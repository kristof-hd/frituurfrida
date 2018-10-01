<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang="nl">
<head>
<vdab:head title='Frituur Frida'/>
</head>
<body>
	<vdab:menu />
	<h1>Vandaag zijn we ${openGesloten}</h1>
	<h2>Adres</h2>
	${adres.straat} ${adres.huisNr}<br>
	${adres.gemeente.postcode} ${adres.gemeente.naam}		
</body>
</html>