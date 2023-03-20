<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enchère Projet</title>
<style><%@include file="/WEB-INF/css/login.css"%></style>
</head>
<body>
	<div id="connect-div">
		<h1>Enchère Projet</h1>
		
		<p>Se Connecter</p>
		<hr>
		
		<c:if test="${ check }">
			<p>Email ou mot de passe incorrect.</p>
		</c:if>
		<form action="Connection" method="post">
			<label for="email">Email : </label>
			<input type="email" name="email" placeholder="example@1234.fr"><br>
			<label for="motdepasse">Mot de Passe : </label>
			<input type="password" name="motdepasse" placeholder="••••••••"><br>
			<input type="submit" value="Se connecter">
		</form>
		
		<a href="Inscription">S'inscrire</a>
	</div>
	
</body>
</html>