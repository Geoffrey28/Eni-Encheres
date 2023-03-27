<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Les objets sont nos amis - Accueil</title>
		<style><%@include file="/WEB-INF/css/admin.css"%></style>
		<%@include file="/WEB-INF/header.jspf"%>
	</head>
	<body>
	
		<div id="admin-div">
		
			<div id="admin-user">
			
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
						<tr><td colspan="4"><hr><td></tr>
					</thead>
					
					<tbody>
						
						<c:forEach items="${ listeUtilisateur }" var="u">
							<tr>
								<td>${ u.noUtilisateur }</td>
								<td>${ u.pseudo }</td>
								<td>${ u.email }</td>
								<td>${ u.credit }</td>
								<td class="admin-action">
									<form action="adminUserDelete" method="post"><input type="hidden" name="noUtilisateur" value="${ u.noUtilisateur }"><input type="submit" value="Supprimer"></form>
									<form action="adminUserDisable" method="post"><input type="hidden" name="noUtilisateur" value="${ u.noUtilisateur }"><input type="submit" value="Désactiver"></form>
								</td>
							</tr>
						</c:forEach>
						
					</tbody>
				</table>
			
			</div>
			
			<div id="admin-categorie">
			
				<p>Gestion Categorie</p>
				
				<hr>
				
				<table>
					<thead>
						<tr>
							<td>Id Categorie</td>
							<td>libelle</td>
							<td></td>
						</tr>
						<tr><td colspan="2"><hr><td></tr>
					</thead>
					<tbody>
						
						<c:forEach items="${ listeCategorie }" var="c">
							<tr>
								<td>${ c.noCategorie }</td>
								<td>${ c.libelle }</td>
								<td class="admin-action">
								<form action="adminCategorieDelete" method="post"><input type="hidden" name="noCategorie" value="${ c.noCategorie }"><input type="submit" value="Supprimer"></form>
								<form action="adminCategorieEdit" method="post"><input type="hidden" name="noCategorie" value="${ c.noCategorie }"><input type="submit" value="Modifier"></form>
								</td>
							</tr>
						</c:forEach>
						
						<tr>
							<td colspan="3">
								<form action="adminCategorieAdd" method="post" style="display: flex;
    justify-content: space-evenly;">
								
								<input type="text" name="libelle" placeholder="libelle">
								
								<input type="submit" value="Ajouter">
								
								</form>
							</td>
						</tr>
						
					</tbody>
				</table>
			
			</div>
		
		</div>
	
	</body>
	
</html>