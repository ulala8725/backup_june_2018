<%@page pageEncoding="UTF-8" %>
<%-- <%@taglib prefix="f" uri="http://sastruts.seasar.org/functions" %> --%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:te="http://www.seasar.org/teeda/extension" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>login page</title>
<script src="/myseasar2/resources/jquery-3.3.1.js"></script>
<%-- <script src="${f:url('/resources/jquery-3.3.1.js')}" type="text/javascript"></script> --%>
</head>
<body>
<script>
$(document).ready(function(){
	$("#joinBtn").click(function(){
		alert("会員登録ページに移動します。");
		$("#fm").attr("action", "/myseasar2/login/join");
	});
});
</script>
<html:errors/>
<s:form action="/login/login" method="POST" styleId="fm">
<table>
	<tr>
		<td>ID</td>
		<td><html:text property="id" /></td>
	</tr>
	<tr>
		<td>password</td>
		<td><html:text property="password" /></td>
	</tr>
	<tr>
		<td colspan="2">
		<s:submit>login</s:submit>
		<button id="joinBtn">join</button>		
		</td>
	</tr>
</table>
</s:form>
</body></html>