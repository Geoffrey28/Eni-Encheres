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
				<div id="accueil-form-filtre">
				
						<div id="filter-name">
						
							<label for="article-name">Nom de l'article :</label>
							<input type="text" name="article-name">
						
						</div>
				
						<div id="filter-categorie">
						
					 		<label for="categorie">Catégorie :</label>
					 		<select name="categorie" size="1">  	
					 			<option value="" selected>Toutes</option>
					 			<c:forEach items="${ listeCategorie }" var="c">
									<option value="${ c.noCategorie }">${ c.libelle }</option>
								</c:forEach>
					   		</select>
				   		
				   		</div>
				   		
				   		<div id="filter-achats-ventes">
				   		
					   		<select name="achat-vente" size="1"> 
					   			<option selected>Achats</option>
					   			<option>Mes ventes</option>
					   		</select> 
					   		
					   		<div id="achats">
					   			<p>Enchères ouvertes<input type="checkbox" class="achats-check" name="open" checked></p>
					   			<p>Mes enchères<input type="checkbox" class="achats-check" name="enchere"></p>
					   			<p>Mes enchères remportés<input type="checkbox" class="achats-check" name="enchere-win"></p>
					   		</div>
					   		<div id="ventes" style="display: none;">
					   			<p>Mes ventes en cours<input type="checkbox" class="achats-check" name="open" checked></p>
					   			<p>Ventes non débutées<input type="checkbox" class="achats-check" name="enchere"></p>
					   			<p>Ventes terminées<input type="checkbox" class="achats-check" name="enchere-win"></p>
					   		</div>
				   		
				   		</div>
				   		
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