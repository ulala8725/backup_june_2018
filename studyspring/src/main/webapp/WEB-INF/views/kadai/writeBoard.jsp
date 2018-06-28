<%@page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>writeBoard</title>
	<script src="/spring/resources/jquery-3.3.1.js"></script>
</head>
<body>
<script>
$(function(){
	$("#deleteBtn").on("click", deleteCheck);
	$("#returnBtn").on("click", returnBoard);
});

function deleteCheck(){
	if ($("#title").val() == "" || $("#contents").val() == "") {
		alert("全項目を入力してください。");
		return false;
	} else {
		$("#fm").submit();
	}
}

function returnBoard(){
	location.href = "/spring/board/gotoboard";
	return false; // trueの場合、formのaction経路に移動されてしまう。
}
	

</script>

<!-- id表示 -->
<p id="message">${sessionScope.loginId }さん、書いています。</p>
${requestScope.message }
<br>

<form id="fm" action="writeBoard" method="post" enctype="multipart/form-data"> <!--  -->
<table border="1">
	<tr>
		<th>作成者</th>
		<td>${sessionScope.loginId }</td>
	</tr>
	<tr>
		<th>タイトル</th>
		<td><input type="text" id="title" name="title" width="auto" style="border-style:none;" 
				placeholder="タイトルを書いてください。"></td>
	</tr>
	<tr>
		<th>添付ファイル</th>
		<td><input type="file" name="uploadFile" /></td>
	</tr>
	<tr height="20px">
		<th>内容</th>
		<td><textarea id="contents" name="contents" rows="10" cols="50" style="resize:none;"
				onfocus="if(this.value == '内容を書いてください。'){this.value=''}"
				onblur="if(this.value==''){this.value='内容を書いてください。'}">内容を書いてください。</textarea></td>
	</tr>
</table>
<button id="deleteBtn">確認</button>
<button type="reset">リセット</button>
<input type="hidden" name="id" value="${sessionScope.loginId }" />
<button id="returnBtn">戻る</button>
</form>
</body>
</html>
