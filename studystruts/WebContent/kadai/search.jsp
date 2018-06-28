<%@page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>検索画面</title>
</head>
<body>
<script type="text/javascript">
	window.onload = function(){
		document.getElementById("id").focus();
	}
	
	function enrollBtn(){return true;}
	function logoutBtn(){return true;}
	function modifyBtn(){return true;}
	function deleteBtn(){
		var deleteBtn = document.getElementById("delete");
		deleteBtn.value = "delete";
		return true;
	}
	function searchAction(){
		var searchBtn = document.getElementById("searchBtn");
		searchBtn.value = "searchBtn";
		return true;
	}
	
</script>

<!-- id表示 -->
<bean:write name="id"/>さん、ようこそ！<br>

<!-- エラーメッセージ出力 -->
<html:messages id="errors" message="false">
	<bean:write name="errors"/>
	<br>
</html:messages>
<html:messages id="messages" message="true">
	<bean:write name="messages"/>
	<br>
</html:messages>

<!-- 検索条件 -->
<div style="text-align:center;">
	<p style="font-size:20px;">※前一致で検索します</p>
	<p style="font-size:35px; font-weight:bold;">検索画面</p>
	
	<html:form action="/Search">
		<table border="1" align="center">
			<tr>
				<td>ID</td>
				<td colspan="3"><html:text property="id" styleId="id" value=""></html:text></td>
			</tr>
			<tr>
				<td>名前</td>
				<td colspan="3"><html:text property="name" ></html:text></td> <!-- value="" -->
			</tr>
			<tr>
				<td>カナ</td>
				<td colspan="3"><html:text property="kana" value=""></html:text></td>
			</tr>
			<tr>
				<td colspan="4">
					<html:submit property="search" value="検索" styleId="searchBtn" onclick="return searchAction()"></html:submit>
					<form action="/Search">
					<input type="submit" name="enroll" value="新規登録" onclick="return enrollBtn()" />
					<input type="submit" name="logout" value="ログアウト" onclick="return logoutBtn()" />
					</form>
				</td>
			</tr>
		</table>
	
<br>
<logic:notEmpty name="list">
<!-- 検索結果　 -->
		<table border="1" align="center">
			<tr>
				<td>ID</td>
				<td>名前</td>
				<td>カナ</td>
				<td>生年月日</td>
				<td>委員会</td>
				<td align="justify" colspan="2">操作</td>
			</tr>
			<tr>
			<logic:iterate id="member" name="list">
				<td><bean:write name="member"></bean:write></td>
			</logic:iterate>
				<td>
<!-- 					<form action="/Search"> -->
					<input type="submit" name="modify" value="更新" onclick="return modifyBtn()" />
					<input type="hidden" name="list" value='<bean:write name="list"/>'>
					<input type="submit" id="delete" name="delete" value="削除" onclick="return deleteBtn()" />
<!-- 					</form> -->
				</td>
			</tr>
		</table>
	
</logic:notEmpty>
</html:form>
</div>	
	
</body>
</html:html>