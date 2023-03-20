<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
 <%@page import="fr.eni.ecole.BO.Utilisateur" %>
 <%@page import="java.util.List,fr.eni.ecole.BO.Role" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>

<form method="POST" action="modifier">
<input type="hidden" name="id" value="${user.id}">
nom:<input type="text" name="nom" value="${user.nom}"><br/>
prenom:<input type="text" name="prenom" value="${user.prenom}"><br/>
email:<input type="email" name="email" value="${user.email}"><br/>
numero : <input type="number" name="numero" value="${user.adresse.numero}">
rue:<input type="text" name="rue" value="${user.adresse.rue}"><br/>
code postal : <input type="number" name="codePostal" value="${user.adresse.codePostal}">
ville:<input type="text" name="ville" value="${user.adresse.ville}"><br/>
role : <select name="role">
<c:forEach items="${roles}" var="r">

		<option value="${r.id}" 
			<c:if test="${r.id == user.role.id}">
				selected="selected" 
			</c:if>
		>${r.nom}
		</option>

</c:forEach>
		
	   </select>

<input type="submit" name="action" value="modifier">
</form>

</body>
</html>