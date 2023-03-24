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
	
		<div>
		
			<p>Gestion Utilisateur</p>
			
			<hr>
			
			<table>
				<thead>
					<tr>
						<td>Id Utilisateur</td>
						<td>Pseudo</td>
						<td>Email</td>
						<td>Credit</td>
						<td></td>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach items="${ listeUtilisateur }" var="u">
						<tr>
							<td>${ u.noUtilisateur }</td>
							<td>${ u.pseudo }</td>
							<td>${ u.email }</td>
							<td>${ u.credit }</td>
							<td><form action="adminDelete" method="post"><input type="hidden" name="noUtilisateur" value="${ u.noUtilisateur }"><input type="submit" value="Supprimer"></form></td>
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
		
		</div>
	
	</body>
	
</html>