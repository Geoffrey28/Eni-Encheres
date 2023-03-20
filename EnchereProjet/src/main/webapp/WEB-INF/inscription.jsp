<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KILOUTOU - Inscription</title>
</head>
<body>
	<h1>KILOUTOU</h1>

	<h3>Inscription</h3>
	<form action="Inscription" method="post">
		<label for="nom">Nom : </label>
		<input type="text" name="nom" placeholder="Dupont" required><br>
		<label for="prenom">Prénom : </label>
		<input type="text" name="prenom" placeholder="Jean" required><br>
		<label for="telephone">Téléphone : </label>
		<input type="tel" name="telephone" placeholder="0644302212" required><br>
		<label for="email">Email : </label>
		<input type="email" name="email" placeholder="jean@dupont.fr" required><br>
		<label for="motdepasse">Mot de passe : </label>
		<input type="password" name="motdepasse" placeholder="••••••••" required><br>
		<label for="numero">Numéro de rue : </label>
		<input type="number" name="numero" placeholder="8" required><br>
		<label for="rue">Nom de rue : </label>
		<input type="text" name="rue" placeholder="Rue des lilas" required><br>
		<label for="codepostal">Code postal : </label>
		<input type="number" name="codepostal" placeholder="60200" required><br>
		<label for="ville">Ville : </label>
		<input type="text" name="ville" placeholder="Compiègne" required><br>
		<label for="typePermis">Permis : </label>
		<select name="typePermis" required>
			<option hidden="true">-- Sélectionnez le permis que vous possédez --</option>
			<option value="A">A</option>
			<option value="B">B</option>
			<option value="Moto">Moto</option>
		</select><br>
		<input type="submit" value="S'inscrire">
	</form>

</body>
</html>