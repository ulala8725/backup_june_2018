<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>変更入力画面</title>
</head>
<body>
<script type="text/javascript">
	function inputBirth(date){
		var birthText = document.getElementById("birth");
		var birth = document.getElementById("mBirth");
		
		if (date.length == 4 || date.length == 7) {
			birthText.value += "-";
		}
		
		birth.value = birthText.value;
	}
	
	function goback(){
		var returnPage = document.getElementById("returnSearchPage");
		returnPage.value = "returnSearchPage";
		return true;
	}

</script>
<html:messages id="errors" message="false">
	<bean:write name="errors"/><br>
</html:messages>

<div align="center">
	<p style="font-size:8">【データ変更入力】</p>
	<html:form action="/Search">
	<table border="1">
		<tr>
			<td>ユーザID</td>
			<td><bean:write name="SearchForm" property="mId"/></td>
		</tr>
		<tr>
			<td>名前</td>
			<td><input type="text" name="name" value='<bean:write name="SearchForm" property="mName"/>' ></td>
		</tr>
		<tr>
			<td>カナ</td>
			<td><input type="text" name="kana" value='<bean:write name="SearchForm" property="mKana"/>'></td>
		</tr>
		<tr>
			<td>生年月日</td>
			<td><input type="text" id="birth" value='<bean:write name="SearchForm" property="mBirth"/>' 
						onkeyup="inputBirth(this.value)">
				<html:hidden property="mBirth" styleId="mBirth" />
			</td>
		</tr>
		<tr>
			<td>委員会</td>
			<td><input type="text" name="club" value='<bean:write name="SearchForm" property="mClub"/>'></td>
		</tr>
		<tr>
			<td colspan="2">
			<html:submit property="modifyUser" value="確認"></html:submit>
			<input type="submit" id="returnSearchPage" name="returnSearchPage" value="戻る" onclick="return goback()"/>
			</td>
		</tr>
	</table>
	</html:form>
</div>
	
</body>
</html:html>