<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KILOUTOU - Voitures</title>
</head>
<body>
	<h1>KILOUTOU</h1>

	<h3>Ajouter véhicule</h3>
	<form action="ListeVehicules" method="post">
		<label for="modele">Modèle : </label>
		<input type="text" name="modele" placeholder="Karl" required><br>
		<label for="marque">Marque : </label>
		<input type="text" name="marque" placeholder="Opel" required><br>
		<label for="immatriculation">Immatriculation : </label>
		<input type="text" name="immatriculation" placeholder="AA123BB" required><br>
		<label for="puissance">Puissance :</label>
		<input type="number" name="puissance" placeholder="70" required>ch/kW<br>
		<label for="statut">Statut : </label>
		<select name="statut" required>
			<option hidden="true">-- Sélectionnez le statut --</option>
			<option value="NL">Non Loué</option>
			<option value="LO">Loué</option>
			<option value="RE">Réservé</option>
		</select><br>
		<label for="prixLocation">Prix location : </label>
		<input type="number" name="prixLocation" placeholder="50" required>€/jour<br>
		<label for="motorisation">Motorisation : </label>
		<select name="motorisation" required>
			<option hidden="true">-- Selectionnez la motorisation --</option>
			<option value="ES">Essence</option>
			<option value="GA">Gazoil</option>
			<option value="EL">Electrique</option>
			<option value="ET">Ethanol</option>
		</select><br>
		<label for="nbrPortes">Nombre de portes : </label>
		<input type="number" name="nbrPortes" placeholder="5" required><br>
		<input type="submit" value="Ajouter">
	</form>
	
	<hr>
	<c:if test="${ !empty voiture }">
		<h3>Dernier véhicule ajouté</h3>
		
		<p>Marque : ${ voiture.marque }</p>
		<p>Modèle : ${ voiture.modele }</p>
		
		<hr>
	</c:if>
	
	<h3>Liste de véhicules</h3>
	
	<table>
		<thead>
	 		<tr>
	 			<th>Modèle</th>
	 			<th>Marque</th>
	 			<th>Immatriculation</th>
	 			<th>Puissance</th>
	 			<th>Statut</th>
	 			<th>Prix location (par jour)</th>
	 			<th>Motorisation</th>
	 			<th>Nombre portes</th>
	 		</tr>
		</thead>
		<tbody>
			<c:forEach items="${ listeVoitures }" var="v">
				<tr>
					<td>${ v.modele }</td>
					<td>${ v.marque }</td>
					<td>${ v.immatriculation }</td>
					<td>${ v.puissance } ${ v.motorisation == "EL" ? "kW" : "ch" }</td>
					<td>${ v.statut }</td>
					<td>${ v.prixLocation }</td>
					<td>${ v.motorisation == "ES" ? "Essence" : v.motorisation == "GA" ? "Gazoil" : v.motorisation == "EL" ? "Electrique" : "Ethanol" }</td>
					<td>${ v.nbrPortes }</td>
					<td><a href="SupprimerVoiture?id=${v.id}">supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a href="Accueil">Retour</a>
	
</body>
</html>