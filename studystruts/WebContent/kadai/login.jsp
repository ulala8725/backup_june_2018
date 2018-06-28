<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ログイン</title>
</head>
<body>
<script type="text/javascript">
	window.onload = function(){
		document.getElementById("id").focus();
	}
	
</script>

<!-- id表示 -->
<logic:notEmpty name="id">
	<bean:write name="id"/>さん、ようこそ！<br>
</logic:notEmpty>

<html:messages id="errors" message="false">
	<bean:write name="errors"/>
	<br>
</html:messages>
	
	<html:form action="/Login">
		ユーザーID<html:text property="id" styleId="id" value=""></html:text>
		<br>
		パスワード<html:password property="pass" value=""></html:password>
		<br>
		<html:submit property="login" value="ログイン"></html:submit>
	</html:form>
</body>
</html:html>