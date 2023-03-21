<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Les objets sont nos amis - Accueil</title>
		<style><%@include file="/WEB-INF/css/acceuil.css"%></style>
	</head>
	<body>
		<header id="header-user">
			<nav>
    			<h2>ENI - Encheres</h2>
       			<div>
       				<a href="inscription">Inscription</a> | <a href="connexion">Connexion</a>
        		</div>	
			</nav>
			<hr>
		</header>
	
		<main>
			<div id="connect-div">
			<h2>Liste des enchères</h2>
			<hr>

			<form class="search-form">
				<h3>Filtres :</h3>
 				<input type="text" placeholder="Nom de l'article">
 				<h5>Catégorie :</h5>
 				<select name="Catégorie" size="1" >  	
    			<option value="toutes">Toutes</option>
    			<option value="Informatique">Informatique</option>
    			<option value="Ammeublement">Ammeublement</option>
    			<option value="Vêtement">Vêtement</option>
    			<option value="Sport&Loisirs">Sport&Loisirs</option>
    			</select>
    			<button type="submit">Rechercher</button>
 			</form>
 			</div>
		</main>
	
	</body>
	
</html>