<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Les objets sont nos amis - Mot de passe oublié</title>
<link rel="icon" type="image/x-icon" href="https://cdn-icons-png.flaticon.com/512/3297/3297954.png">
<style><%@include file="/WEB-INF/css/PasswordOublier.css"%></style>
<%@include file="/WEB-INF/header.jspf"%>
</head>
<body>
	<div id="connect-div">
	<h2>Mot de passe oublié</h2>
		<hr>
		<div>
			<br>
				<form method="post">
	 				<label for="pseudo">Pseudo:</label>
	 				<input type="text" id="pseudo" name="Pseudo" placeholder="Entrez votre pseudo" required checked><br>
 					<label for="motdepasse">Nouveau mot de passe : </label>
					<input type="password" name="password" placeholder="Entrez un nouveau mot de passe" id="password" required><br>
					<label for="motdepasseconfirmation">Confirmation nouveau mot de passe : </label>
					<input type="password" placeholder="Confirmer nouveau mot de passe" id="confirm_password" required><br>
					<input type="submit" value="Modifier le mot de passe">
				</form>
		</div>
	</div>
</body>
</html>