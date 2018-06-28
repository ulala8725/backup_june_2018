<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:te="http://www.seasar.org/teeda/extension" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>join page-Client Validator-</title>
<html:javascript formName="loginActionForm_enroll" />
<script src="/myseasar2/resources/jquery-3.3.1.js"></script>
</head>
<body>
<html:errors />
<s:form action="/login">
<table>
	<tr>
		<td>id</td>
		<td><html:text property="id" /></td>
	</tr>
	<tr>
		<td>password</td>
		<td><html:text property="password" /></td>
	</tr>
	<tr>
		<td>password check</td>
		<td><html:text property="rePassword" /></td>
	</tr>
	<tr>
		<td>e-mail</td>
		<td><html:text property="email" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<s:submit property="enroll">submitBtn</s:submit> <!-- clientValidate="true" -->
		<input type="reset" value="resetBtn"/>
		</td>
	</tr>
</table>
</s:form>
</body>
</html>