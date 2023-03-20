<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page import="java.util.List,fr.eni.ecole.BO.Utilisateur" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<tr>
<th>nom</th>
<th>prenom</th>
<th>email</th>
<th>ville</th>
<th></th>
<th></th>
</tr>
<c:choose>

<c:when test="${userConnected.role.nom eq 'admin'}">
	<c:forEach items="${liste}" var="u">
		<tr>
		<td>${u.nom}</td>
		<td>${u.prenom}</td>
		<td>${u.email}</td>
		<td>${u.adresse.ville}</td>
		<td><a href="modifier?id=${u.id}">modifier</a></td>
		<td><a href="supprimer?id=${u.id}">supprimer</a></td>
		</tr>
	</c:forEach>
</c:when>
	<c:otherwise>
		<c:forEach items="${liste}" var="u">
			<tr>
			<td>${u.nom}</td>
			<td>${u.prenom}</td>
			<td>${u.email}</td>
			<td>${u.adresse.ville}</td>
			<c:if test="${userConnected.id ==u.id}">
				<td><a href="modifier?id=${u.id}">modifier</a></td>
				<td><a href="supprimer?id=${u.id}">supprimer</a></td>
			</c:if>
			</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>
</table>
</body>
</html>