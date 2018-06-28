<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>kadaiB-29</title>
</head>
<body>
	<p style="font-size:20; text-align:center; font-weight:bold">出力画面</p>
	<c:forEach items="${list}" var="item">
		<c:if test="${list!= null}">
			${item}<br>
		</c:if>
	</c:forEach>
</body>
</html>