<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	String idCanBeUsed = request.getParameter("idCanBeUsed"); 
	String passCanBeUsed = request.getParameter("passCanBeUsed"); 
	String passCheckCanBeUsed = request.getParameter("passCheckCanBeUsed"); 
	String nameCanBeUsed = request.getParameter("nameCanBeUsed");
	String kanaCanBeUsed = request.getParameter("kanaCanBeUsed"); 
	String birthCanBeUsed = (String)session.getAttribute("birthCanBeUsed"); // session (input tag)
	String clubCanBeUsed = request.getParameter("clubCanBeUsed");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>新規登録入力画面</title>
</head>
<body>
<script>
	window.onload = function(){
		document.getElementById("id").focus();
	}

	function idCheck(){
		var idCheckForm = document.getElementById("idCheckForm");
		idCheckForm.submit();
	}

	function inputBirth(date){
		if (<%=clubCanBeUsed%> != null) {
			alert("alalalla")
			return;
		}
		var birthText = document.getElementById("birthText");
		var birth = document.getElementById("birth");
		
		if (date.length == 4 || date.length == 7) {
			birthText.value += "/";
		}
		
		birth.value = birthText.value;
	}
	
</script>

<!-- エラーメッセージ出力 -->
<html:messages id="errors" message="false">
	<bean:write name="errors"/>
	<br>
</html:messages>

<!-- 入力画面 -->
<div style="text-align:center;">
	<html:form action="/Enroll">
	<table>
		<tr>
			<td rowspan="2">ID</td>
			<td>
				<logic:empty name="idCanBeUsed">
				<html:text property="id" styleId="id" value=""></html:text>
				</logic:empty>
				
				<logic:notEmpty name="idCanBeUsed">
				<html:text property="id" styleId="id" value="<%=idCanBeUsed %>"></html:text>
				</logic:notEmpty> 
<%-- 				<bean:write name="${idCanBeUsed}"/> --%>
<%-- <bean:write name ="session<%=aaa.toString() %>"/> --%>
				
			</td>
		</tr>
		<tr>
			<td>
				<form action="/Enroll" id="idCheckForm" >
				<button name="idCheck" value="idCheck" onclick="idCheck()">使用できるか確認</button>
				</form>
				<html:messages id="messages" message="true">
					<bean:write name="messages"/>
				</html:messages>
			</td>
		</tr>
		<tr>
			<td>パスワード:</td>
			<td>
				<logic:empty name="passCanBeUsed">
				<html:password property="pass" value=""></html:password>
				</logic:empty>
				<logic:notEmpty name="passCanBeUsed">
				<html:password property="pass" value="<%=passCanBeUsed %>"></html:password>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td>パスワード再入力:</td>
			<td>
				<logic:empty name="passCheckCanBeUsed">
				<html:password property="passCheck" value=""></html:password>
				</logic:empty>
				<logic:notEmpty name="passCheckCanBeUsed">
				<html:password property="passCheck" value="<%=passCheckCanBeUsed %>"></html:password>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td>名前:</td>
			<td>
			<logic:empty name="nameCanBeUsed">
			<html:text property="name" value=""></html:text>
			</logic:empty>
			<logic:notEmpty name="nameCanBeUsed">
			<html:text property="name" value="<%=nameCanBeUsed %>"></html:text>
			</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td>カナ:</td>
			<td>
			<logic:empty name="kanaCanBeUsed">
			<html:text property="kana" value=""></html:text>
			</logic:empty>
			<logic:notEmpty name="kanaCanBeUsed">
			<html:text property="kana" value="<%=kanaCanBeUsed %>"></html:text>
			</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td>生年月日(yyyy/mm/dd):</td>
			<td>
				<logic:empty name="birthCanBeUsed">
				<input type="text" id="birthText" value="" maxlength="10" onkeyup="inputBirth(this.value)"/>
				<html:hidden property="birth" styleId="birth"/>
				</logic:empty>
				
				<logic:notEmpty name="birthCanBeUsed">
				<input type="text" id="birthText" value="<%=birthCanBeUsed %>"
					maxlength="10" onkeyup="inputBirth(this.value)"/>
				<html:hidden property="birth" styleId="birth" />
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td>委員会:</td>
			<td>
				<logic:empty name="clubCanBeUsed">
				<html:text property="club" value=""></html:text>
				</logic:empty>
				<logic:notEmpty name="clubCanBeUsed">
				<html:text property="club" value="<%=clubCanBeUsed %>"></html:text>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" name="enrollBtn" value="登録します" />
				<input type="submit" name="returnBtn" value="戻る" />
			</td>
		</tr>
	</table>	
	</html:form>
</div>
</body>
</html:html>