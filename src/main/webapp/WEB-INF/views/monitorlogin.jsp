<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8" session="false"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="pt">
<head>
<!-- 
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
-->

<meta http-equiv="X-UA-Compatible" content="IE=Edge" />

<title>Monitor de Tecnologias Integradas</title>
<meta name="generator" content="Bootply" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />

<link rel="stylesheet"
	href="<c:url value="/resources/bootstrap/3.3.7/css/bootstrap.min.css"/>">

<script
	src="<c:url value="/resources/jquery/2.2.4/jquery-2.2.4.min.js"/>"></script>
<script
	src="<c:url value="/resources/bootstrap/3.3.7/js/bootstrap.min.js"/>"></script>

<!--[if IE]>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<![endif]-->

<!--[if lt IE 9]>
			<script src=<c:url value="/resources/bootstrap/html5shiv.js"/></script>		
			
<![endif]-->

<link href="<c:url value="/resources/styles/styles.css"/>"
	rel="stylesheet">

<link href="<c:url value="/resources/images/logo_infolog_riscos.gif"/>"
	rel="icon" type="image/x-icon" />

</head>

		<div>
			<c:forEach items="${monitorLogin.lstLogin}" var="login">
				<p><c:out value="${monitorLogin.ctlUser}" /></p>
				<p><c:out value="${monitorLogin.nomUser}" /></p>
				<p><c:out value="${monitorLogin.codSenha}" /></p>
				<p><c:out value="${monitorLogin.numNivac}" /></p>
				
			</c:forEach>>
			
		</div>	
</body>
</html>