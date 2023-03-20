<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KILOUTOU - Accueil</title>
</head>
<body>
	<h1>KILOUTOU</h1>
	
	<h3>Bonjour ${ clientConnecte.nom }</h3>
	
	<a href="Profil">Profil</a>
	<a href="ListeVehicules">Liste véhicules</a>
</body>
</html>