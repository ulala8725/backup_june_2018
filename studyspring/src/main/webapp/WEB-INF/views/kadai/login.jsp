<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ログイン</title>
</head>
<body>
<c:if test="${message != null}">
	${message}
</c:if>

	<form action="login" method="post"> <!-- login -->
		ユーザーID<input type="text" name="id" id="id" value="" autofocus="autofocus"/>
		<br>
		パスワード<input type="password" name="pass" value="" />
		<br>
		自動ログイン設定<input type="checkbox" name="autoLogin" value="true"/>
		<br>
		<input type="submit" name="login" value="ログイン" />
	</form>
</body>
</html>