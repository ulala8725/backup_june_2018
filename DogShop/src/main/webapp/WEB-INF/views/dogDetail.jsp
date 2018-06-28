<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>詳細情報</title>
</head>
<body>
	<h1 align="center">${vo.kind }の情報</h1>
	<p align="right" style="color:yellow;"><a href="deleteCart">ショッピングカート</a></p>
	<!-- ${pageContext.request.contextPath} == dogshop-->
	<table>
		<tr bgcolor="yellow">
			<td align="right">
				照会数： ${vo.readcount }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="addCart?id=${vo.id }">ショッピングカートに入れる</a>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<td rowspan="6"><img src="${pageContext.request.contextPath}/resources/image/${vo.image }.jpg"></td>
			<td>種類：　${vo.kind }</td>
		</tr>
		<tr><td>出生地：　${vo.country }</td></tr>
		<tr><td>価格：　${vo.price }</td></tr>
		<tr><td>平均身長：　${vo.height }m</td></tr>
		<tr><td>平均体重：　${vo.weight }kg</td></tr>
		<tr><td>特徴：　${vo.weight }kg</td></tr>
		<tr>
			<td colspan="2" align="center"><a href="dogsList?id=0">リストにもどる</a></td>
		</tr>
	</table>
</body>
</html>