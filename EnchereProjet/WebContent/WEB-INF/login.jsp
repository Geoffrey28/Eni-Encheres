<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Les objets sont nos amis</title>
</head>
<body>
<form action="login" method="post">

Enchère Projet

<p>email : <input type="email" name="email" value="${cookie.lastLogin.value}"></p>
<p>mot de passe : <input type="password" name="password"></p>
<input type="submit" value="valider">

</form>

</body>
</html>