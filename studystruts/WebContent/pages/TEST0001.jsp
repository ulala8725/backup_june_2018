<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>ログイン画面</title>
</head>
<body>
	<!-- 実際の処理を実行するactionクラスを指定する -->
	<html:form action="/TEST0001">
		<table border="0">
		<tr>
			<td>名前</td>
			<td><html:text property="loginId" /></td>
		</tr>
		<tr>
			<td>パスワード</td>
			<td><html:password property="passWord" /></td>
		</tr>
		<tr>
			<td colspan="2"><html:submit value="ログイン"></html:submit></td>
		</tr>
		</table>
	</html:form>
</body>
</html:html>
