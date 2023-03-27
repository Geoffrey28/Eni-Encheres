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
							<td>Désactiver</td>
							<td></td>
						</tr>
						<tr><td colspan="5"><hr><td></tr>
					</thead>
					
					<tbody>
						
						<c:forEach items="${ listeUtilisateur }" var="u">
							<tr>
								<td>${ u.noUtilisateur }</td>
								<td>${ u.pseudo }</td>
								<td>${ u.email }</td>
								<td>${ u.credit }</td>
								<td><c:if test="${ u.disabled == true }"><img src="https://cdn-icons-png.flaticon.com/512/753/753345.png"></c:if> <c:if test="${ u.disabled == false }"><img src="https://cdn-icons-png.flaticon.com/512/2251/2251677.png"></c:if> </td>
								<td class="admin-action">
									<form action="AdminUserDelete" method="post"><input type="hidden" name="noUtilisateur" value="${ u.noUtilisateur }"><input type="submit" value="Supprimer"></form>
									<c:if test="${ u.disabled == false }"><form action="AdminUserDisable" method="post"><input type="hidden" name="noUtilisateur" value="${ u.noUtilisateur }"><input type="submit" value="Désactiver"></form></c:if>
									<c:if test="${ u.disabled == true }"><form action="AdminUserEnable" method="post"><input type="hidden" name="noUtilisateur" value="${ u.noUtilisateur }"><input type="submit" value="Activer"></form></c:if>
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
								<form action="AdminCategorieDelete" method="post"><input type="hidden" name="noCategorie" value="${ c.noCategorie }"><input type="submit" value="Supprimer"></form>
								<input type="submit" value="Modifier" onclick="editCategorie(${ c.noCategorie })">
								
								<form method="post" action="AdminCategorieEdit" style="display:none" id="Categorie_${ c.noCategorie }" class="form-categorie-edit">
									<input type="hidden" name="noCategorie" value="${ c.noCategorie }">
									<input type="text" name="libelle">
									<input type="submit" value="Valider">
								</form>
								
								</td>
							</tr>
						</c:forEach>
						
						<tr><td colspan="3"><hr></td></tr>
						<tr>
							<td colspan="3">
								<form action="AdminCategorieAdd" method="post" style="display: flex; justify-content: space-evenly;">
								
								<input type="text" name="libelle" placeholder="libelle">
								
								<input type="submit" value="Ajouter">
								
								</form>
							</td>
						</tr>
						
						
					</tbody>
				</table>
			
			</div>
		
		</div>
		
		<script>
		
		function editCategorie(noCategorie){
			
			const categorie = Array.from(
				document.getElementsByClassName('form-categorie-edit')
			);

			categorie.forEach(box => {
				box.style.display = 'none';
			});
			
			document.getElementById("Categorie_" + noCategorie).style.display = "block";
			
		}
		
		</script>
	
	</body>
	
</html>