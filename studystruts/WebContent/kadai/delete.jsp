<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>削除</title>
</head>
<body>
<script type="text/javascript">
	function goback(){
		var returnPage = document.getElementById("returnSearchPage");
		returnPage.value = "returnSearchPage";
		return true;
	}
	
	function ddd(){
		if (confirm("削除しますか。")) {
			var deleteUser = document.getElementById("deleteUser");
			deleteUser.value = "deleteUser";
			alert(deleteUser.value)
			return true;
		} else return false;
	}
</script>

	<p style="color:blue; font-size:20px">削除確認:</p>
	<div align="center">
	<html:form action="/Search">
		<table border="1">
		<tr>
			<td>ユーザID</td>
			<td><bean:write name="SearchForm" property="mId"/></td>
		</tr>
		<tr>
			<td>名前</td>
			<td><bean:write name="SearchForm" property="mName"/></td>
		</tr>
		<tr>
			<td>カナ</td>
			<td><bean:write name="SearchForm" property="mKana"/></td>
		</tr>
		<tr>
			<td>生年月日</td>
			<td><bean:write name="SearchForm" property="mBirth"/></td>
		</tr>
		<tr>
			<td>委員会</td>
			<td><bean:write name="SearchForm" property="mClub"/></td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="submit" id="deleteUser" name="deleteUser" value="削除" onclick="return ddd()"/>
			<input type="submit" id="returnSearchPage" name="returnSearchPage" value="戻る" onclick="return goback()"/>
			</td>
		</tr>
	</table>
	</html:form>
	</div>
	
</body>
</html:html>