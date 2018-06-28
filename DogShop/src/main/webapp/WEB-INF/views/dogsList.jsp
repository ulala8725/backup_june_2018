<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>犬リスト</title>
</head>
<body>
	<div align="center">
	<h1 align="center">犬リスト</h1>
	<!-- ${pageContext.request.contextPath} == dogshop-->
	<p align="right" style="color:yellow;"><a href="deleteCart">ショッピングカート</a></p>
	<table>
		<tr>
			<c:forEach items="${alldogsList }" var="vo">
			<td>
				<a href="getDogDetail?id=${vo.id }&image=${vo.image}"><img src="${pageContext.request.contextPath}/resources/image/${vo.image }.jpg" height="200" width="200"></img></a>
			</td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach items="${alldogsList }" var="vo">
			<td>商品名： ${vo.kind }</td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach items="${alldogsList }" var="vo">
			<td>価格： ${vo.price }</td>
			</c:forEach>
		</tr>
	</table>
	<br>
	<h1 align="center">今日チェックしたリスト</h1>
		<c:if test="${cookieList == null}">
			<p>チェックしたリストがありません。</p>
		</c:if>
		<c:if test="${cookieList != null}">
	<table>
		<tr>
		<c:forEach items="${cookieList }" var="cookieVo">
			<td>
				<a href="getDogDetail?id=${cookieVo.id }&image=${cookieVo.image}"><img src="${pageContext.request.contextPath}/resources/image/${cookieVo.image }.jpg" height="200" width="200"></img></a>
			</td>
		</c:forEach>
		</tr>
	</table>
		</c:if>
	</div>
</body>
</html>