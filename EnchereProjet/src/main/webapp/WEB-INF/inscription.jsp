<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/css/inscription.css"%></style>
<%@include file="/WEB-INF/header.jspf"%>
<title>ENCHERES - Inscription</title>


</head>
<body>

	
	<c:if test="${ empty userConnected}"><h3>Inscription</h3></c:if>
	<c:if test="${ !empty userConnected }"><h3>Modifier le profil de ${ user.pseudo }</h3></c:if>
	
<!-- User déconnecté -->
<c:if test="${ empty userConnected }">

	<form action="Inscription" method="post">
	<div class="form">
		
			<label for="pseudo">Pseudo : </label>
			<input type="text" name="pseudo" placeholder="Pseudo" required><br>
			
			<label for="prenom">Prénom : </label>
			<input type="text" name="prenom" placeholder="Prénom" required><br>
			
			<label for="telephone">Téléphone : </label>
			<input type="tel" name="telephone" placeholder="Téléphone" required><br>
			
			<label for="codepostal">Code postal : </label>
		 	<input type="number" name="codepostal" placeholder="Code Postal" required><br>
		 	
		 	<label for="motdepasse">Mot de passe : </label>
		 	<input type="password" name="password" placeholder="Mot de passe" id="password" required><br>
		 	
		 	<label for="nom">Nom : </label>
		 	<input type="text" name="nom" placeholder="Nom" required><br>
		 					 
			<label for="email">Email : </label>
		 	<input type="email" name="email" placeholder="Email" required><br>
			
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
		 <div class="boutons">
			 <a href="accueil.jsp">
				<button type="submit">Créer</button>
			</a>
		</div>
				
	</form>
	<div class="boutons">
		<a href="accueil.jsp">
			<button>Annuler</button>
		</a>
	</div>
	
	</c:if>
	
	
<!-- User connecté -->
	<c:if test="${ !empty userConnected }">
	

	
		<form action="Modification" method="post">
	<div class="form">
		
			<label for="pseudo">Pseudo : </label>
			<input type="text" name="pseudo" placeholder="Pseudo" required><br>
			
			<label for="prenom">Prénom : </label>
			<input type="text" name="prenom" placeholder="Prénom" required><br>
			
			<label for="nom">Nom : </label>
		 	<input type="text" name="nom" placeholder="Nom" required><br>
			
			<label for="telephone">Téléphone : </label>
			<input type="tel" name="telephone" placeholder="Téléphone" required><br>
			
			<label for="codepostal">Code postal : </label>
		 	<input type="number" name="codepostal" placeholder="Code Postal" required><br>
		 	
		 <c:if test="${ check }">
			<p>Email ou mot de passe incorrect.</p>
		 	<label for="motdepasse">Mot de passe : </label>
		 	<input type="password" name="password" placeholder="Ancien Mot de passe" id="password" required><br>
		 	
		</c:if>
		
			<label for="email">Email : </label>
		 	<input type="email" name="email" placeholder="Email" required><br>
			
			<label for="rue">Nom de rue : </label>
		 	<input type="text" name="rue" placeholder="Nom de rue" required><br>	
		 			
			<label for="ville">Ville : </label>
		 	<input type="text" name="ville" placeholder="Ville" required><br>
		 	
		 	<label for="nouveaumotdepasse">Confirmation : </label>
		 <input type="password" placeholder="Nouveau mot de passe" id="new_password" required><br>
		 		 
		 <label for="motdepasseconfirmation">Confirmation : </label>
		 <input type="password" placeholder="Confirmer mot de passe" id="confirm_password" required><br>
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
		 
		 <div class="boutons">
			 <a href="accueil.jsp">
				<button type="submit">Modifier</button>
			</a>
		</div>
				
	</form>
	
	<div class="boutons">
		<a href="accueil.jsp">
			<button>Supprimer mon compte</button>
		</a>
	</div>
	
	</c:if>
</html>