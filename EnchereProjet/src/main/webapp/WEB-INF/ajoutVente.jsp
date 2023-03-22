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
				<label for="article">Article : </label>
				<input type="text" name="article" required>
			</div>
			<div>
				<label for="description">Description : </label>
				<textArea name="description" rows="4" cols="30" maxlength="128" required></textArea>
			</div>
			<div>
				<label for="category">Catégorie : </label>
				<select name="category" required>
					<option hidden="true">- Sélectionnez une catégorie -</option>
					<option value="Decoration">Décoration</option>
					<option value="Animaux">Animaux</option>
					<option value="Sports">Sports</option>
					<option value="Automobile">Automobile</option>
				</select>
			</div>
			<div id="newAuction-picture">
				<p>Photo : </p>
				<div>
					<input type="file" id="picture" name="picture">
					<label for="picture">
						<span>Uploader</span>
					</label>
				</div>
			</div>
			<div id="newAuction-price">
				<label for="price">Mise à prix : </label>
				<input type="number" name="price" required>
			</div>
			<div>
				<label for="auctionStart">Début de l'enchère : </label>
				<input type="date" name="auctionStart" required>
			</div>
			<div>
				<label for="auctionEnd">Fin de l'enchère : </label>
				<input type="date" name="auctionEnd" required>
			</div>
			<fieldset>
				<legend>Retrait</legend>
				<div>
					<label for="street">Rue : </label>
					<input type="text" name="street" required>
				</div>
				<div>
					<label for="zipCode">Code postal : </label>
					<input type="text" name="zipCode" maxlength="5" required>
				</div>
				<div>
					<label for="city">Ville : </label>
					<input type="text" name="city" required>
				</div>
			</fieldset>
			<div id="newAuction-submit">
				<input type="submit" value="Enregistrer">
				<a href="Accueil">Annuler</a>
			</div>
		</form>
	</div>
</body>
</html>