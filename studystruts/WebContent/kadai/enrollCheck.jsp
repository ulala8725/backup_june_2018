<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規登録確認画面</title>
</head>
<body>
<script type="text/javascript">
	function goback(){
		var returnPage = document.getElementById("returnPage");
		returnPage.value = "returnForm";
		return true;
	}
	
	function enrollConform(){
		if (confirm("登録しますか？")) {
			return true;
		} else return false
	}
	
</script>

	<p style="font-align:center; font-size:30px; font-weight:bold;">【入力確認します】</p>
	
	<html:form action="/Enroll">
	<table border="1">
		<tr align="center">
			<td>項目</td>
			<td width="150">入力内容</td>
		</tr>
		<tr>
			<td>ID</td>
			<td><bean:write name="idCanBeUsed"/></td>
		</tr>
		<tr>
			<td>パスワード</td>
			<td><bean:write name="passCanBeUsed"/></td>
		</tr>
		<tr>
			<td>名前</td>
			<td><bean:write name="nameCanBeUsed"/></td>
		</tr>
		<tr>
			<td>カナ</td>
			<td><bean:write name="kanaCanBeUsed"/></td>
		</tr>
		<tr>
			<td>生年月日</td>
			<td><bean:write name="birthCanBeUsed"/></td>
		</tr>
		<tr>
			<td>委員会</td>
			<td><bean:write name="clubCanBeUsed"/></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<html:submit property="enrollConfirmBtn" value="登録します" onclick="return enrollConform()"/>
				<form id="returnForm" action="/Enroll">
				<input type="submit" name="checkReturnBtn" value="戻る" onclick="return goback()" />
				<input type="hidden" name="returnPage" id="returnPage" />
				</form>
			</td>
		</tr>
	</table>
	</html:form>
</body>
</html>