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
				 			<option value="" selected>Toutes</option>
				 			<c:forEach items="${ listeCategorie }" var="c">
								<option value="${ c.noCategorie }">${ c.libelle }</option>
							</c:forEach>
				   		</select>
					</div>
		    		<input type="submit" value="Rechercher">
				</div>
		 	</form>
			<hr>
			<div id="accueil-liste">
				<c:forEach items="${ listeArticleVendu }" var="a">
					<article id="accueil-article">		
						<img alt="Image vente" src="https://cdn-icons-png.flaticon.com/512/251/251319.png">
						<div>
							<c:choose>
								<c:when test="${ !empty userConnected }">
									<a href="EnchereDetail?id=${ a.noArticle }">${ a.nomArticle }</a>
								</c:when>
								<c:when test="${ empty userConnected }">
									<p id="accueil-article-nom">${ a.nomArticle }</p>
								</c:when>
							</c:choose>
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