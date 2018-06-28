<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規登録確認画面</title>
<script src="/spring/resources/jquery-3.3.1.js"></script>
<style>
	.text{
		border: none;
		border-color: transparent;
	}
</style>
</head>
<body>
<script type="text/javascript">
	$(function(){
		$("#enrollConfirmBtn").on("click", enrollConfirm)
	});
	
	function enrollConfirm(){
		if (confirm("登録しますか？")) {
			$("#fm").submit();
		} else return false
	}
	
</script>

	<p style="font-align:center; font-size:30px; font-weight:bold;">【入力確認します】</p>
	
	<form id="fm" action="search" method="post">
	<table border="1">
		<tr align="center">
			<td>項目</td>
			<td width="150">入力内容</td>
		</tr>
		<tr>
			<td>ID</td>
			<td><input type="text" class="text" name="id" value="${vo.id }" readonly="readonly" /></td>
		</tr>
		<tr>
			<td>パスワード</td>
			<td><input type="text" class="text" name="pass" value="${vo.pass }" readonly="readonly" /></td>
		</tr>
		<tr>
			<td>名前</td>
			<td><input type="text" class="text" name="name" value="${vo.name }" readonly="readonly" /></td>
		</tr>
		<tr>
			<td>カナ</td>
			<td><input type="text" class="text" name="kana" value="${vo.kana }" readonly="readonly" /></td>
		</tr>
		<tr>
			<td>生年月日</td>
			<td><input type="text" class="text" name="birth" value="${vo.birth }" readonly="readonly" /></td>
		</tr>
		<tr>
			<td>委員会</td>
			<td><input type="text" class="text" name="club" value="${vo.club }" readonly="readonly" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button id="enrollConfirmBtn" name="submit" value="enrollConfirm">登録します</button>
				<button name="submit" value="enrollReturn">戻る</button>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>