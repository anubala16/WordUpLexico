<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="utf-8">
<title>Word-Up Lexico: Your Study Partner</title>
<link rel="stylesheet" href="../styles/main.css" type="text/css" />
</head>
<nav class="navBar"> <!-- 
<c:choose>
	<c:when test="${lesson == null}">
lesson is null (nested)
	<img alt="Word-Up Logo" src="images/Logo.gif" width=120>
		<a class="menu" href="index.jsp">Word-Up Home</a>
		<a class="menu" href="welcome.jsp">My Home</a>
		<a class="menu" href="faq.jsp">FAQ</a>
		<a class="menu" href="sitemap.jsp">Sitemap</a>
		<a class="menu" href="contact.jsp">Contact US</a>
	</c:when>
	<c:otherwise>
	lesson is valid (nested)
	<img alt="Word-Up Logo" src="../images/Logo.gif" width=120>
		<a class="menu" href="../index.jsp">Word-Up Home</a>
		<a class="menu" href="../welcome.jsp">My Home</a>
		<a class="menu" href="../faq.jsp">FAQ</a>
		<a class="menu" href="../sitemap.jsp">Sitemap</a>
		<a class="menu" href="../contact.jsp">Contact US</a>
	</c:otherwise>
</c:choose>
--> 
	<img alt="Word-Up Logo" src="images/Logo.gif" width=120> 
	<a class="menu" href="index.jsp">Word-Up Home</a> 
	<a class="menu" href="welcome.jsp">My Home</a> <a class="menu" href="faq.jsp">FAQ</a> 
	<a class="menu" href="sitemap.jsp">Sitemap</a> 
	<a class="menu" href="contact.jsp">Contact US</a> </nav>
<body>