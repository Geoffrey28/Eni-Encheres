<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KILOUTOU - Connection</title>
</head>
<body>
	<h1>KILOUTOU</h1>

	<h3>Connection</h3>
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
</body>
</html>