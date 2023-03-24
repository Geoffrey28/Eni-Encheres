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
			<form id="accueil-form" action="Accueil" method="post">
				<div id="accueil-form-filtre">
						<div id="filter-name" style="display: grid;">
						
							<label for="article-name">Nom de l'article :</label>
							<input type="text" name="article-name" value="${ name }">
							
							<label for="categorie">Catégorie :</label>
					 		<select name="categorie" size="1">  	
					 			<option value="-1" <c:if test="${ categorie == -1 }">selected</c:if>>Toutes</option>
					 			<c:forEach items="${ listeCategorie }" var="c">
									<option value="${ c.noCategorie }" <c:if test="${ categorie == c.noCategorie }">selected</c:if>>${ c.libelle }</option>
								</c:forEach>
					   		</select>
						
						</div>
				   		
				   		<div id="filter-achats-ventes">
				   		
					   		<select name="achat-vente" id="achat-vente" size="1" onchange="switchAchatVentes()"> 
					   			<option value="0" selected>Achats</option>
					   			<option value="1">Mes ventes</option>
					   		</select> 
					   		
					   		<div id="achats">
					   			<p>Enchères ouvertes<input type="checkbox" class="achats-check" checked></p>
					   			<p>Mes enchères<input type="checkbox" class="achats-check"></p>
					   			<p>Mes enchères remportés<input type="checkbox" class="achats-check"></p>
					   		</div>
					   		<div id="ventes" style="display: none;">
					   			<p>Mes ventes en cours<input type="checkbox" class="ventes-check" checked></p>
					   			<p>Ventes non débutées<input type="checkbox" class="ventes-check"></p>
					   			<p>Ventes terminées<input type="checkbox" class="ventes-check"></p>
					   		</div>
				   		
				   		</div>
				   		
				   		<input type="hidden" name="checked-info" value="0">
				   		
				   		<div style="align-self: center;">
							<input type="submit" value="Appliquer" style="width: 300px;">
						</div>
				   		
				</div>
		 	</form>
			<hr>
			<div id="accueil-liste">
				<c:forEach items="${ listeArticleVendu }" var="a">
					<article class="accueil-article">	
					
						<div class="div-img">
							<img alt="Image vente" class="img-vente" src="${ a.img }">
						</div>	
						
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
	
	<script type="text/javascript">
	
		function switchAchatVentes(){
			if (document.getElementById("achat-vente").value == 0) {
				document.getElementById("achats").style.display = "";
				document.getElementById("ventes").style.display = "none";
			} else {
				document.getElementById("achats").style.display = "none";
				document.getElementById("ventes").style.display = "";
			}
		}
		
		
	
	</script>
	
	</body>
	
</html>