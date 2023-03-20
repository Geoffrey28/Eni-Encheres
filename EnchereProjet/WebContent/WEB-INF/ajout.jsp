<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
 <%@page import="java.util.List,fr.eni.ecole.BO.Role" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<form method="POST" action="ajouterutilisateur">
nom:<input type="text" name="nom"><br/>
prenom:<input type="text" name="prenom"><br/>
email:<input type="email" name="email"><br/>
mot de passe : <input type="password" name="motDePasse">
numero : <input type="number" name="numero">
rue:<input type="text" name="rue"><br/>
code postal : <input type="number" name="codePostal">
ville:<input type="text" name="ville"><br/>
role : <select name="role">
<c:forEach items="${roles}" var="r">

		<option value="${r.id}">${r.nom}</option>

</c:forEach>		
	   </select>
<input type="submit" name="action" value="ajouter">
</form>

</body>
</html>