<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ENI-Enchères - Profil</title>
<style><%@include file="/WEB-INF/css/profil.css"%></style>
<%@include file="/WEB-INF/header.jspf"%>
</head>
<body>
	<div id="profil">
	
		<c:if test="${ empty user }"><h2>Mon Profil</h2></c:if>
		<c:if test="${ !empty user }"><h2>Profil de ${ user.pseudo }</h2></c:if>
		<div id="profil-content">
			<c:if test="${ empty user }">
			
				<div>
					<p>Pseudo :</p>
					<p>${ userConnected.pseudo }</p>
				</div>
				<div>
					<p>Nom :</p>
					<p>${ userConnected.nom }</p>
				</div>
				<div>
					<p>Prénom :</p>
					<p>${ userConnected.prenom }</p>
				</div>
				<div>
					<p>Email :</p>
					<p>${ userConnected.email }</p>
				</div>
				<div>
					<p>Téléphone :</p>
					<p>${ userConnected.telephone }</p>
				</div>
				<div>
					<p>Rue :</p>
					<p>${ userConnected.rue }</p>
				</div>
				<div>
					<p>Code postal :</p>
					<p>${ userConnected.codePostal }</p>
				</div>
				<div>
					<p>Ville :</p>
					<p>${ userConnected.ville }</p>
				</div>
				<a href="Inscription">Modifier</a>
			</c:if>
			
			<c:if test="${ !empty user }">
				<div>
					<p>Pseudo :</p>
					<p>${ user.pseudo }</p>
				</div>
				<div>
					<p>Nom :</p>
					<p>${ user.nom }</p>
				</div>
				<div>
					<p>Prénom :</p>
					<p>${ user.prenom }</p>
				</div>
				<div>
					<p>Email :</p>
					<p>${ user.email }</p>
				</div>
				<div>
					<p>Téléphone :</p>
					<p>${ user.telephone }</p>
				</div>
			</c:if>
		</div>		
	</div>
</body>
</html>