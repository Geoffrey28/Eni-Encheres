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
		<h2>Nouvelle vente</h2>	
		<form id="newAuction-form" action="AjoutVente" method="post">
			<div>
				<label for="nomArticle">Article : </label>
				<input type="text" name="nomArticle">
			</div>
			<div>
				<label for="description">Description : </label>
				<textArea name="description" rows="4" cols="30" maxlength="128"></textArea>
			</div>
			<div>
				<label for="categorie">Catégorie : </label>
				<select name="categorie">
					<option hidden="true">- Sélectionnez une catégorie -</option>
					<option value="1">Vêtements</option>
					<option value="2">Ameublement</option>
					<option value="3">Informatique</option>
					<option value="4">Sports&Loisirs</option>
				</select>
			</div>
			<div id="newAuction-picture">
				<p>Photo : </p>
				<div>
					<input type="file" id="picture" name="image">
					<label for="image">
						<span>Uploader</span>
					</label>
				</div>
			</div>
			<div id="newAuction-price">
				<label for="prix">Mise à prix : </label>
				<input type="number" name="prix">
			</div>
			<div>
				<label for="dateDebut">Début de l'enchère : </label>
				<input type="date" name="dateDebut" required>
			</div>
			<div>
				<label for="dateFin">Fin de l'enchère : </label>
				<input type="date" name="dateFin" required>
			</div>
			<fieldset>
				<legend>Retrait</legend>
				<div>
					<label for="rue">Rue : </label>
					<input type="text" name="rue" value="${ userConnected.rue }">
				</div>
				<div>
					<label for="codePostal">Code postal : </label>
					<input type="text" name="codePostal" maxlength="5" value="${ userConnected.codePostal }">
				</div>
				<div>
					<label for="ville">Ville : </label>
					<input type="text" name="ville" value="${ userConnected.ville }">
				</div>
			</fieldset>
			<div id="newAuction-submit">
				<input type="submit" value="Enregistrer" >
				<a href="Accueil">Annuler</a>
			</div>
		</form>
	</div>
</body>
</html>