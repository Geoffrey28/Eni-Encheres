<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Les objets sont nos amis - Détails enchères</title>
<style><%@include file="/WEB-INF/css/ajoutVente.css"%></style>
<%@include file="/WEB-INF/header.jspf"%>
</head>
<body>
	<div id="newAuction-img">
		<img alt="Image vente" src="${ article.img }">
	</div>
	<div id="newAuction-div">
		<h2>Détails de la vente</h2>	
		<form id="newAuction-form" action="EnchereDetail" method="post">
			<div style="place-content: center; font-size: larger;">
				${ article.nomArticle }
			</div>
			<hr>
			<div>
				<span><p>Description :</p><p>${ article.description }</p></span>
			</div>
			<div>
				<span><p>Catégorie :</p><p>${ categorie.libelle }</p></span>
			</div>
			<div>
				<span>
					<p>Meilleur Offre :</p>
					<c:choose>
						<c:when test="${ article.prixVente != article.miseAPrix }">
							<p>
								${ article.prixVente } 
								<c:if test="${ !empty encherisseur }">
									(${ encherisseur.pseudo })
								</c:if>
								</p>
						</c:when>
						<c:when test="${ article.prixVente == article.miseAPrix }">
							<p>Aucune Enchère</p>
						</c:when>
					</c:choose>
				</span>
			</div>
			<div>
				<span><p>Mise à prix :</p><p>${ article.miseAPrix }</p></span>
			</div>
			<div>
				<span><p>Fin de l'enchère :</p><p>${ article.dateFinEncheres }</p></span>
			</div>
			<div>
				<span>
					<p>Retrait :</p>
					<p style="text-align: right;">
						${ retrait.rue } <br>
						${ retrait.codePostal } ${ retrait.ville }
					</p>
				</span>
			</div>
			<div>
				<span><p>Vendeur :</p><p>${ user.pseudo }</p></span>
			</div>
			<c:choose>
				<c:when test="${ user.noUtilisateur != userConnected.noUtilisateur && article.etatVente == 'Ec' }">
					<div>
						<span>
							<p>Ma Proposition :</p>
							<p>
								<input type="hidden" name="id" value="${ article.noArticle }">
								<input type="number" name="montant" style="width: 75px;" min="${ article.prixVente + 1 }">
								<input type="submit" value="Enchérir" style="cursor: pointer;">
							</p>
						</span>
					</div>
				</c:when>
				<c:when test="${ user.noUtilisateur == userConnected.noUtilisateur && article.etatVente != 'Ec' }">
					<a href="ModifierVente?id=${ article.noArticle }">Modifier</a>
				</c:when>			
			</c:choose>
		</form>
	</div>
</body>
</html>