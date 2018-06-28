<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>削除</title>
<script src="/spring/resources/jquery-3.3.1.js"></script>
</head>
<body>
<script type="text/javascript">
	$(function(){
		$("#deleteBtn").on("click", deleteUser);
	});
	
	function deleteUser(){
		if (confirm("削除しますか。")) {
			$("#deleteUserId").val("${vo.id}");
			$("#fm").submit();
		} else return false;
	}
</script>

	<p style="color:blue; font-size:20px">削除確認:</p>
	<div align="center">
	<form id="fm" action="/spring/search" method="post">
		<table border="1">
		<tr>
			<td>ユーザID</td>
			<td>${vo.id }</td>
		</tr>
		<tr>
			<td>名前</td>
			<td>${vo.name }</td>
		</tr>
		<tr>
			<td>カナ</td>
			<td>${vo.kana }</td>
		</tr>
		<tr>
			<td>生年月日</td>
			<td>${vo.birth }</td>
		</tr>
		<tr>
			<td>委員会</td>
			<td>${vo.club }</td>
		</tr>
		<tr>
			<td colspan="2">
			<button name="submit" id="deleteBtn" value="deleteConfirm">削除</button>
			<input type="hidden" id="deleteUserId" name="deleteUserId"/>
			<input type="button" value="戻る" onclick="history.back()"/>
			</td>
		</tr>
	</table>
	</form>
	</div>
	
</body>
</html>