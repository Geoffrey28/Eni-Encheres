<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Les objets sont nos amis - Connexion</title>
<style><%@include file="/WEB-INF/css/login.css"%></style>
<%@include file="/WEB-INF/header.jspf"%>
</head>
<body>
	<div id="connect-div">
		<img src="https://cdn-icons-png.flaticon.com/512/456/456112.png">
		<hr>
		
		<c:if test="${ check }">
			<p>Email ou mot de passe incorrect.</p>
		</c:if>
		<form action="Connection" method="post">
			<input type="text" name="pseudo" placeholder="Pseudo ou Email"><br>
			<input type="password" name="motdepasse" placeholder="Mot de Passe"><br>
			<a href="/Enchere-Eni/PasswordOublier">Mot de passe oublié</a>
			<input type="submit" value="Se connecter">
		</form>
	</div>
	
</body>
</html>