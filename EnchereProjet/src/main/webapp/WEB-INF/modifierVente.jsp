<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ENI-Encheres - Ajouter une vente</title>
<style><%@include file="/WEB-INF/css/ajoutVente.css"%></style>
<%@include file="/WEB-INF/header.jspf"%>
</head>
<body>
	<div id="newAuction-img">
		<img alt="Image vente" src="https://cdn-icons-png.flaticon.com/512/251/251319.png">
	</div>
	<div id="newAuction-div">
		<h2>Modifier la vente</h2>	
		<form id="newAuction-form" action="ModifierVente" method="post">
			<div>
				<label for="nomArticle">Article : </label>
				<input type="text" name="nomArticle" value="${ article.nomArticle }">
			</div>
			<div>
				<label for="description">Description : </label>
				<textArea name="description" rows="4" cols="30" maxlength="128" >${ article.description }</textArea>
			</div>
			<div>
				<label for="categorie">Catégorie : </label>
				<select name="categorie">
					<c:forEach items="${ categories }" var="c">
						<option value="${ c.noCategorie }"
							<c:if test="${ c.noCategorie == article.noCategorie }">
								selected="selected"
							</c:if>
							>${ c.libelle }
						</option>
					</c:forEach>
				</select>
			</div>
			<div id="newAuction-picture">
				<p>Photo : </p>
				<div>
					<input type="file" id="image" name="image" value="${ article.img }">
					<label for="image">Uploader</label>
				</div>
			</div>
			<div id="newAuction-price">
				<label for="prix">Mise à prix : </label>
				<input type="number" name="prix" value="${ article.miseAPrix }">
			</div>
			<div>
				<label for="dateDebut">Début de l'enchère : </label>
				<input type="date" name="dateDebut" value="${ dateDebut }" required>
			</div>
			<div>
				<label for="dateFin">Fin de l'enchère : </label>
				<input type="date" name="dateFin" value="${ dateFin }" required>
			</div>
			<fieldset>
				<legend>Retrait</legend>
				<div>
					<label for="rue">Rue : </label>
					<input type="text" name="rue" value="${ retrait.rue }">
				</div>
				<div>
					<label for="codePostal">Code postal : </label>
					<input type="text" name="codePostal" maxlength="5" value="${ retrait.codePostal }">
				</div>
				<div>
					<label for="ville">Ville : </label>
					<input type="text" name="ville" value="${ retrait.ville }">
				</div>
			</fieldset>
			<div id="newAuction-submit">
				<input type="hidden" value="${ article.noArticle }" name="noArticle">
				<input type="submit" value="Enregistrer" >
				<a href="EnchereDetail?id=${ article.noArticle }">Annuler</a>
			</div>
				<a href="AnnulerVente?id=${ article.noArticle }">Supprimer la vente</a>
		</form>
	</div>
</body>
</html>