<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Les objets sont nos amis - Accueil</title>
		<style><%@include file="/WEB-INF/css/accueil.css"%></style>
		<%@include file="/WEB-INF/header.jspf"%>
	</head>
	<body>
	
		<div id="accueil">
			<h2>Liste des enchères</h2>
			<hr>
			<form id="accueil-form">
				<h3>Filtres :</h3>
				<div id="accueil-form-filtre">
					<div>
						<input type="text" placeholder="Nom de l'article"><br>
				 		<label for="categorie">Catégorie :</label>
				 		<select name="categorie" size="1">  	
				   			<option value="toutes">Toutes</option>
			    			<option value="Informatique">Informatique</option>
				   			<option value="Ammeublement">Ameublement</option>
			   				<option value="Vêtement">Vêtement</option>
			   				<option value="SportLoisirs">Sport&Loisirs</option>
				   		</select>
					</div>
		    		<input type="submit" value="Rechercher">
				</div>
		 	</form>
			<hr>
			<div id="accueil-liste">
				<c:forEach items="${ listeArticleVendu }" var="a">
					<article id="accueil-article">		
						<img alt="Image vente" src="https://cdn-icons-png.flaticon.com/512/251/251319.png"><br>
						<div>
							<a href="EnchereDetail?id=${ a.noArticle }">${ a.nomArticle }</a>
							<p>Prix: ${ a.prixVente } points</p>
							<p>Fin de l'enchère: ${ a.dateFinEncheres }</p>
							<p>Vendeur: <a href="Profil?id=${ a.noUtilisateur }">Voir l'utilisateur</a></p>	
						</div>
					</article>
				</c:forEach>
 			</div>
		</div>
	
	</body>
	
</html>