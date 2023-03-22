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
			<!--<div>
				<form class="search-form">
						<h3>Filtres :</h3>
		 				<input type="text" placeholder="Nom de l'article">
		 				<h5>Catégorie :</h5>
		 				<select name="Catégorie" size="1" >  	
		    			<option value="toutes">Toutes</option>
		    			<option value="Informatique">Informatique</option>
		    			<option value="Ammeublement">Ameublement</option>
		    			<option value="Vêtement">Vêtement</option>
		    			<option value="Sport&Loisirs">Sport&Loisirs</option>
		    			</select>
		    			<button type="submit">Rechercher</button>
		 			</form>
			</div> -->
			<div id="accueil-liste">
				<c:forEach items="${ listeArticleVendu }" var="a">
					<article id="accueil-article">	
							<img alt="Image vente" src="https://cdn-icons-png.flaticon.com/512/251/251319.png">
						<div>
							<h1>${ a.nomArticle }</h1>
							<p>Prix : ${ a.prixVente } points</p>
							<p>Fin de l'enchère : ${ a.dateFinEncheres }</p>
							<div id="accueil-article-btn">
								<p>Vendeur : <a href="/Profil?pseudo=${ a.noUtilisateur }">${ a.noUtilisateur }</a></p>
								<a href="/EnchereDetail?id=${ a.noArticle }">Details</a>
							</div>
						</div>
					</article>
				</c:forEach>
 			</div>
		</div>
	
	</body>
	
</html>