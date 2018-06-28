<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ショッピングカート</title>
</head>
<body>
<script>
	window.onload = function(){
		if ("${cartList}" != null) {
			var totalPrice = document.getElementById("totalPrice");
			var priceArray = document.getElementsByClassName("price");
			var checkboxArray = document.getElementsByClassName("qty");
			var total = 0;
			for (var i = 0; i < priceArray.length; i++) {
				total += priceArray[i].value * checkboxArray[i].value;
			}
			
			totalPrice.innerHTML = "総額: " + total.toLocaleString() + "円";
			// toFixed(number) : 小数点以下の桁
			// toPrecision : 四捨五入する小数点の位置
		}
	}
	
	function deleteDog(){
		var checkboxArray = document.getElementsByClassName("checkbox");
		var checkedArray = [];
		for (var i = 0; i < checkboxArray.length; i++) {
			if (checkboxArray[i].checked) {
				checkedArray.push(checkboxArray[i].value);
			}
		}
		
		if (checkedArray.length == 0) {
			alert("選択した犬がありません。");
			return false;
		}
		alert(checkedArray)
		location.href = "deleteCart?checkedArray=" + checkedArray;
	}
</script>
<div align="center">
	<h1 align="center">ショッピングカート</h1>
	<!-- ${pageContext.request.contextPath} == dogshop-->
	<br>
	<p align="right" style="color:yellow;"><a href="dogsList?id=0">ショッピングを続く</a></p>
	<br>
	<c:if test="${cartList == null }">
		<h3>選択した犬がありません。</h3>
	</c:if>
	<c:if test="${cartList != null }">
	<table border="1">
		<tr>
			<th>no.</th>
			<th>犬のイメージ</th>
			<th>犬の種類</th>
			<th>価格</th>
			<th>数量</th>
			<th><button onclick="deleteDog()">削除</button></th>
		</tr>
	<c:forEach items="${cartList }" var="cart" varStatus="status">
		<tr>
			<td>${status.index + 1 }</td>
			<td><img src="${pageContext.request.contextPath}/resources/image/${cart.image }.jpg" width="50" height="50"/></td>
			<td>${cart.kind }</td>
			<td>${cart.price }<input type="hidden" class="price" value="${cart.price }" /></td>
			<td>
				<a href="addQty?id=${cart.id }&qty=${cart.qty+1 }">up</a>
				${cart.qty }
				<a href="addQty?id=${cart.id }&qty=${cart.qty-1 }">down</a>
				<input type="hidden" class="qty" value="${cart.qty }" />
			</td>
			<td><input type="checkbox" class="checkbox" value="${cart.id }"/></td>
		</tr>	
	</c:forEach>
	</table>
	<h3 id="totalPrice" align="right"></h3>
	</c:if>
</div>
</body>
</html>