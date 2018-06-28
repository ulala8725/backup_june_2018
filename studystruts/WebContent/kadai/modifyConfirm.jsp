<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新確認画面</title>
</head>
<body>
<script type="text/javascript">
	function goback(){
		var returnPage = document.getElementById("returnPage");
		returnPage.value = "returnModifyPage";
		return true;
	}
	
	function modifyConfirm(){
		if (confirm("登録しますか？")) {
			var modifyConfirmBtn = document.getElementById("modifyConfirmBtn");
			modifyConfirmBtn.value = "modifyConfirm";
			return true;
		} else return false
	}
	
</script>

	<p style="font-align:center; font-size:30px; font-weight:bold;">【データ変更の確認】</p>
	
	<html:form action="/Search">
	<table border="1">
		<tr>
			<td>ユーザID</td>
			<td width="150"><bean:write name="SearchForm" property="mId" /></td>
		</tr>
		<tr>
			<td>名前</td>
			<td><bean:write name="SearchForm" property="mName" /></td>
		</tr>
		<tr>
			<td>カナ</td>
			<td><bean:write name="SearchForm" property="mKana" /></td>
		</tr>
		<tr>
			<td>生年月日</td>
			<td><bean:write name="SearchForm" property="mBirth" /></td>
		</tr>
		<tr>
			<td>委員会</td>
			<td><bean:write name="SearchForm" property="mClub" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<html:submit property="modifyConfirmBtn" styleId="modifyConfirmBtn" value="確認" onclick="return modifyConfirm()"/>
				<form id="returnForm" action="/Search">
				<input type="submit" value="戻る" onclick="return goback()" />
				<input type="hidden" name="returnModifyPage" id="returnPage" />
				</form>
			</td>
		</tr>
	</table>
	</html:form>
</body>
</html>