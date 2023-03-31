<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Les objets sont nos amis - Nouvelle vente</title>
<link rel="icon" type="image/x-icon" href="https://cdn-icons-png.flaticon.com/512/3297/3297954.png">
<style><%@include file="/WEB-INF/css/ajoutVente.css"%></style>
<%@include file="/WEB-INF/header.jspf"%>
        <script>
            /* Cette fonction permet d'afficher une vignette pour chaque image sélectionnée */
            function readFilesAndDisplayPreview(files) {
                let imageList = document.querySelector('#list'); 
                imageList.innerHTML = "";
                
                for ( let file of files ) {
                    let reader = new FileReader();
                    
                    reader.addEventListener( "load", function( event ) {
                        let span = document.createElement('span');
                        span.innerHTML = '<img height="600" src="' + event.target.result + '" />';
                        imageList.appendChild( span );
                    });

                    reader.readAsDataURL( file );
                }
            }
        </script>
</head>
<body>
	<div id="list">
		<img alt="Image vente" src="https://cdn-icons-png.flaticon.com/512/251/251319.png">
	</div>
	<div id="newAuction-div">
		<h2>Nouvelle vente</h2>	
		<form id="newAuction-form" action="AjoutVente" method="post" enctype="multipart/form-data">
			<div>
				<label for="nomArticle">Article : </label>
				<input type="text" name="nomArticle" required>
			</div>
			<div>
				<label for="description">Description : </label>
				<textArea name="description" rows="4" cols="30" maxlength="128"></textArea>
			</div>
			<div>
				<label for="categorie">Catégorie : </label>
							<select name="categorie" size="1" required>
							<option hidden="true">- Sélectionnez une catégorie -</option>
					 			<c:forEach items="${ listeCategorie }" var="c">
									<option value="${ c.noCategorie }" <c:if test="${ categorie == c.noCategorie }">selected</c:if>>${ c.libelle }</option>
								</c:forEach>
					   		</select>
			</div>
			<div id="newAuction-picture">
				<p>Photo : </p>
				<div>
					<input type="file" id="image" name="image" accept="images/*" multiple
                   onchange="readFilesAndDisplayPreview(this.files);">
                  	<input type="submit" value="/Images" />
					<label for="image">Uploader</label>
				</div>
			</div>
			<div id="newAuction-price">
				<label for="prix">Mise à prix : </label>
				<input type="number" name="prix" required>
			</div>
			<div>
				<label for="dateDebut">Début de l'enchère : </label>
				<input type="datetime-local" id="dateDebut" name="dateDebut" min="${ dateDuJour }" required>
			</div>
			<div>
				<label for="dateFin">Fin de l'enchère : </label>
				<input type="datetime-local" id="dateFin" name="dateFin" min="${ dateDuJour }" required>
			</div>
			<fieldset>
				<legend>Retrait</legend>
				<div>
					<label for="rue">Rue : </label>
					<input type="text" name="rue" value="${ userConnected.rue }" required>
				</div>
				<div>
					<label for="codePostal">Code postal : </label>
					<input type="text" name="codePostal" maxlength="5" value="${ userConnected.codePostal }" required>
				</div>
				<div>
					<label for="ville">Ville : </label>
					<input type="text" name="ville" value="${ userConnected.ville }" required>
				</div>
			</fieldset>
			<div id="newAuction-submit">
				<input type="submit" value="Enregistrer" >
				<a href="Accueil">Annuler</a>
			</div>
		</form>
	</div>
</body>
</html>
<script type="text/javascript">
	var dateDebut = document.getElementById("dateDebut");
	dateDebut.addEventListener("change", function() {
		let date = dateDebut.value;
		document.getElementById("dateFin").setAttribute("min", date);
	});
</script>