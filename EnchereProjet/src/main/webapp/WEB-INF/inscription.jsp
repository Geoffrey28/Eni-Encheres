<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/css/inscription.css"%></style>
<%@include file="/WEB-INF/header.jspf"%>
<title>Les objets sont nos amis - Inscription</title>


</head>
<body>
	<div id="inscription">
		<c:if test="${ empty userConnected}"><h3>Inscription</h3></c:if>
		<c:if test="${ !empty userConnected }"><h3>Modifier le profil de ${ user.pseudo }</h3></c:if>
			
		<!-- User déconnecté -->
		<c:if test="${ empty userConnected }">
		
			<form action="Inscription" method="post">
			<c:if test="${ !errorMessage }">
			 	<p id="errorMessage">${ errorMessage }</p>
			</c:if>
			<div class="form">
				
					<label for="pseudo">Pseudo : </label>
					<input type="text" name="pseudo" placeholder="Pseudo" required><br>
					
					<label for="prenom">Prénom : </label>
					<input type="text" name="prenom" placeholder="Prénom" required><br>
					
					<label for="telephone">Téléphone : </label>
					<input type="tel" name="telephone" pattern="[0-9]{10}" maxlength="10" placeholder="ex: 0344568238" required><br>
					
					<label for="codepostal">Code postal : </label>
				 	<input type="number" name="codepostal" pattern="[0-9]{5}" placeholder="Code Postal" required><br>
				 	
				 	<label for="motdepasse">Mot de passe : </label>
				 	<input type="password" name="password" placeholder="Mot de passe" id="password" required><br>
				 	
				 	<label for="nom">Nom : </label>
				 	<input type="text" name="nom" placeholder="Nom" required><br>
				 					 
					<label for="email">Email : </label>
				 	<input type="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" placeholder="Email" required><br>
					
					<label for="rue">Nom de rue : </label>
				 	<input type="text" name="rue" placeholder="Nom de rue" required><br>	
				 			
					<label for="ville">Ville : </label>
				 	<input type="text" name="ville" placeholder="Ville" required><br>
				 		 
				 <label for="motdepasseconfirmation">Confirmation : </label>
				 <input type="password" placeholder="Confirmer mot de passe" id="confirm_password" required><br>
				</div> 
				 <!-- Script pour vérifier si les mots de passe correspondent -->
				 <script>
					 var password = document.getElementById("password")
					  , confirm_password = document.getElementById("confirm_password");
			
					function validatePassword(){
					  if(password.value != confirm_password.value) {
					    confirm_password.setCustomValidity("Les mots de passes ne correspondent pas");
					  } else {
					    confirm_password.setCustomValidity('');
					  }
					}
			
					password.onchange = validatePassword;
					confirm_password.onkeyup = validatePassword;
				 </script>
				 <div class="buttons">
				 	<input type="submit" value="Créer">
				 	<a href="Accueil">Annuler</a>
				 </div>
				 			
			</form>		
		</c:if>
			
			
		<!-- User connecté -->
		<c:if test="${ !empty userConnected }">
			
		
			
			<form action="Inscription" method="post">
				<c:if test="${ !errorMessage }">
				 	<p id="errorMessage">${ errorMessage }</p>
				</c:if>
				<div class="form">			
					<input type="hidden" value="${userConnected.noUtilisateur}" name="noUtilisateur">

					<label for="pseudo">Pseudo : </label>
					<input type="text" name="pseudo" placeholder="Pseudo" value="${userConnected.pseudo}" required><br>

					<label for="prenom">Prénom : </label>
					<input type="text" name="prenom" placeholder="Prénom" value="${userConnected.prenom}" required><br>
				
					<label for="nom">Nom : </label>
				 	<input type="text" name="nom" placeholder="Nom" value="${userConnected.nom}" required><br>
				
					<label for="nom">Nom : </label>
					<input type="text" name="nom" placeholder="Nom" value="${userConnected.nom}" required><br>
					
					<label for="telephone">Téléphone : </label>
					<input type="tel" name="telephone" placeholder="Téléphone" pattern="[0-9]{10}" maxlength="10" value="${userConnected.telephone}" required><br>
				
					<label for="codepostal">Code postal : </label>
					<input type="number" name="codepostal" placeholder="Code Postal" value="${userConnected.codePostal}" required><br>
				
					<label for="email">Email : </label>
					<input type="email" name="email" placeholder="Email" value="${userConnected.email}" required><br>
					
					<label for="rue">Nom de rue : </label>
					<input type="text" name="rue" placeholder="Nom de rue" value="${userConnected.rue}" required><br>
				
					<label for="ville">Ville : </label>
					<input type="text" name="ville" placeholder="Ville" value="${userConnected.ville}" required><br>

				 	<label for="nouveaumotdepasse">Confirmation : </label>
					<input type="password" placeholder="Nouveau mot de passe" id="new_password" ><br>
	
					<label for="motdepasseconfirmation">Confirmation : </label>
					<input type="password" placeholder="Confirmer mot de passe" id="confirm_password" ><br>
				</div> 
				
				 <!-- Script pour vérifier si les mots de passe correspondent -->
				 <script>
					 var password = document.getElementById("new_password")
					  , confirm_password = document.getElementById("confirm_password");
			
					function validatePassword(){
					  if(password.value != confirm_password.value) {
					    confirm_password.setCustomValidity("Les mots de passes ne correspondent pas");
					  } else {
					    confirm_password.setCustomValidity('');
					  }
					}
					password.onchange = validatePassword;
					confirm_password.onkeyup = validatePassword;
				 </script>
				 <div class="buttons">
					<input type="submit" value="Modifier">
					<a href="Supprimer">Supprimer</a>
				 </div>	 
			</form>
		</c:if>	
	</div>
</body>
</html>