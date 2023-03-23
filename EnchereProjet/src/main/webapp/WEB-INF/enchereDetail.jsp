<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enchère Projet</title>
<style><%@include file="/WEB-INF/css/ajoutVente.css"%></style>
<%@include file="/WEB-INF/header.jspf"%>
</head>
<body>
	<div id="newAuction-img">
		<img alt="Image vente" src="https://cdn-icons-png.flaticon.com/512/251/251319.png">
	</div>
	<div id="newAuction-div">
		<h2>Détail vente</h2>	
		<form id="newAuction-form" action="Encherir" method="post">
			<div>
				${ article.nomArticle }
			</div>
			<div>
				<span><p>Description :</p><p>${ article.description }</p></span>
			</div>
			<div>
				<span><p>Catégorie :</p><p>${ categorie.libelle }</p></span>
			</div>
			<div>
				<span><p>Meilleur Offre :</p><p>${ article.prixVente }</p></span>
			</div>
			<div>
				<span><p>Mise à prix :</p><p>${ article.miseAPrix }</p></span>
			</div>
			<div>
				<span><p>Fin de l'enchère :</p><p>${ article.dateFinEncheres }</p></span>
			</div>
			<div>
				<span><p>Retrait :</p><p>lieu retrait</p></span>
			</div>
			<div>
				<span><p>Vendeur :</p><p>${ user.pseudo }</p></span>
			</div>
			<div>
				<span><p>Ma Proposition :</p><p><input type="number" style="width: 75px;" min="${ article.prixVente }"> <input type="submit" value="Enchérir" style="cursor: pointer;"></p></span>
			</div>
		</form>
	</div>
</body>
</html>