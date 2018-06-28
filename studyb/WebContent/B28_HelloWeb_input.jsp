<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>kadaiB-28 input</title>
</head>
<body>
<script type="text/javascript" charset="utf-8">
function callAlert(){
	var text = document.getElementById("textId")
	var form = document.getElementById("form")
	if (text.value.length != 0) {
		form.submit()
	} else {
		alert("空です。")
	return false;
	}
}
</script>
	<p style="font-size:20; text-align:center; font-weight:bold">入力画面</p>
	<form method="post" action="B28" id="form" target="_parent"> <!-- _top -->
		<input type="text" name="text" id="textId" autofocus="autofocus">
		<input type="button" onclick="callAlert()" value="クエリ送信">
	</form>

</body>
</html>