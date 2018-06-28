<%@ page language="java" contentType="text/html; charset=utf-8" 
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>kadaiB-30</title>
</head>
<body>
<script type="text/javascript">
	window.onload = function(){
		//check boxに以前の状態を維持する
		var contentCount = "${contentArray}".split(",");
		if (contentCount != "") {
			for (var i = 0; i < contentCount.length; i++) {
				if (contentCount[i] == "TESTNO") {
					document.getElementById("testno").checked = "checked";
				}
				if (contentCount[i] == "NAME") {
					document.getElementById("name").checked = "checked";
				}
				if (contentCount[i] == "KANA") {
					document.getElementById("kana").checked = "checked";
				}
			}// for
		}// outer if
		
		//text boxに以前の状態を維持する
		if ("${preText}" != "") {
			document.getElementById("text").value = "${preText}";
		}
		
		//select boxに以前の状態を維持する
		if ("${preSelect}" != "") {
			document.getElementById("${preSelect}Select").selected = "true";
		}
		
		//radio buttonに以前の状態を維持する
		if ("${preSort}" != "") {
			if ("${preSort}" == "ASC") {
				document.getElementById("ASC").checked = "checked";
			} else document.getElementById("DESC").checked = "checked";
		}
	}

	function search(){
		//ElementsByCalssName or Name:複数の値がArrayに入る
		var contents = document.getElementsByClassName("checkbox");
		//contentを配列にいれて渡すための変数
		var contentArrayId = document.getElementById("contentArrayId");
		//ElementById or Name:ただ一つの値が入る
		var text = document.getElementById("text");
		var select = document.getElementById("select");
		//formタグ内の内容を伝送する
		var fm = document.getElementById("fm");
		
		//contentを配列にいれて渡す
		var contentArray = [];
		for (var i = 0; i < contents.length; i++) {
			if(contents[i].checked) contentArray.push(contents[i].value)
		}
		
		if (contentArray.length <= 0) {
			alert("表示内容一つ以上選択してください。");
			return false;
		}
		
		//content配列をcontentArrayIdタグに入れる(input type=hidden)
		contentArrayId.value = contentArray;
		
		fm.submit();
	}
</script>
	<p style="font-size:3em; text-align:left; font-weight:bold">検索画面</p>
	<br>
	<form method="post" action="B30" id="fm">
	<table border="1">
		<tr>
			<td>表示内容</td>
			<td>
				<input type="checkbox" class="checkbox" id="testno" value="TESTNO">TESTNO
				<input type="checkbox" class="checkbox" id="name" value="NAME">NAME
				<input type="checkbox" class="checkbox" id="kana" value="KANA">KANA
			</td>
		</tr>
		<tr>
			<td>検索条件</td>
			<td>
				<input type="text" id="text" name="text" value="">(NAME前万一致)
			</td>
		</tr>
		<tr>
			<td>ソート</td>
			<td>
				<select id="select" name="select">
					<option id="testnoSelect" value="testno">TESTNO</option>
					<option id="nameSelect" value="name">NAME</option>
					<option id="kanaSelect" value="kana">KANA</option>
				</select>
				<input type="radio" id="ASC" name="sort" value="ASC" checked="checked">昇順
				<input type="radio" id="DESC" name="sort" value="DESC">降順
			</td>
		</tr>
	</table>
	
	<input type="button" value="検索" onclick="search()">
	<input type="hidden" id="contentArrayId" name="contentArray">
	</form>
	
	<hr>
	
	<c:if test="${list!= null}">
		<table border="1">
			<tr>
		<c:forEach items="${contentCount }" var="content">
				<td>${content}</td>
		</c:forEach>
			</tr>
			
		<c:forEach items="${list }" var="list"> <!-- ArrayList -->
				<tr>
			<c:forEach items="${fn:split(list,':')}" var="item">　<!-- ArrayListをまた':'に分ける -->
	<%-- 			<c:if test="${item != null || item != 0}"> --%>
					<td>${item}</td>
	<%-- 			</c:if> --%>
			</c:forEach>
				</tr>
		</c:forEach>
		
		
		</table>
	</c:if>
	<c:if test="${list == null}">検索結果がありません。</c:if>
	
</body>
</html>