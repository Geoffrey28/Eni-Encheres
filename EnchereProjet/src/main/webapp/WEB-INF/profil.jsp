<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KILOUTOU - Profil</title>
</head>
<body>
	<h1>KILOUTOU</h1>
	
	<h3>Profil</h3>
	<ul>
		<li>
			<label>Nom : </label>
			<input type="text" value="${ clientConnecte.nom }">
		</li>
		<li>
			<label>Prénom : </label>
			<input type="text" value="${ clientConnecte.prenom }">
		</li>
		<li>
			<label>Téléphone : </label>
			<input type="text" value="${ clientConnecte.telephone }">
		</li>
		<li>
			<label>Type de permis : </label>
			<input type="text" value="${ clientConnecte.typePermis }">
		</li>
		<li>
			<label>Email : </label>
			<input type="email" value="${ clientConnecte.email }">
		</li>
		<li>
			<label>Mot de passe : </label>
			<input type="password" value="${ clientConnecte.password }">
		</li>		
	</ul>
	<a href="Accueil">Retour</a>
</body>
</html>