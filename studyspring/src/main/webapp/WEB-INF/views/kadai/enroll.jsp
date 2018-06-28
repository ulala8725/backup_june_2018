<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>新規登録入力画面</title>
<script src="/spring/resources/jquery-3.3.1.js"></script>
</head>
<body>
<!-- <script src="resources/enroll.js"> -->
<script type="text/javascript">
$(function() {
//     $("#idCheck").on("click", idCheck);//動的イベントで作られたものなのでこれには反応しない
    $(document).on("click", "#idCheck", idCheck);
    $("#enrollBtn").on("click", enrollCheck);
//     $("#idConfirm").on("click", idConfirm); //動的イベントで作られたものなのでこれには反応しない
	$(document).on("click", "#idConfirm", idConfirm);
	$(document).on("click", "#enrollBtn", enrollCheck);
	$(document).on("keyup", "#birthText", inputBirth);
    
});

function idCheck() {
	$("#id").attr("readonly", false);
	//空白チェック
	if ($("#id").val() == "") {
		alert("idを入力してください。")
		return;
	}
	//英数字チェック
	if ($("#id").val().match(/[^A-Za-z0-9]+/) != null) {
		alert("idは英数字だけ使えます。")
		return;
	}
	$.ajax({
		url:"/spring/search/search",
		type:"post",
// 		data:{"id=" + $("#id").val()},
		data:{"id" : $("#id").val()},
		success: function(result){
			if (result) {
				alert("使用不可：すでに登録されたIDです。");
			} else {
				alert("使用可能なIDです。");
				$("#idBtnDiv").html("<button type='button' id='idCheck' value='idCheck'>使用できるか確認</button>");
				$("#idBtnDiv").append("<input type='button' id='idConfirm' value='使用確定'/>");
			}
		}
	});
}// idCheck

function idConfirm() {
	$("#id").attr("readonly", true);
}// idConfirm

function inputBirth(){
	var birthText = $("#birthText").val();
	if (birthText.length == 4 || birthText.length == 7) {
		$("#birthText").val(birthText + "/") ;
	}
	$("#birth").val(birthText);
}

function enrollCheck() {
	var id = $("#id").val();
	var pass = $("#pass").val();
	var passCheck = $("#passCheck").val();
	var name = $("#name").val();
	var kana = $("#kana").val();
	var birth = $("#birth").val();
	var club = $("#club").val();
	//空白チェック
	if (id == "" || pass == "" || passCheck == "" || name == "" || kana == "" || birth == "" || club == "") {
		alert("全項目を入力してください。");
		return false;
	}
	//id確定
	if ($("#id").attr("readonly") != "readonly") {
		alert("idの使用確定をしてください。");
		return false;
	}
	//id
	if (!id.match(/^[0-9a-zA-Z]+$/)) {
		alert("idは半角英数字だけ入力してください。");
		return false;
	}
	//password
	if (pass != passCheck) {
		alert("パスワードを確認してください。")
		return false;
	}
	//名前
	if (!name.match(/^[^\x01-\x7E\xA1-\xDF]+$/)) {
		alert("名前は全角で入力してください。");
		return false;
	}
	//カタカナ
	if (!kana.match(/^[\x20-\x7e]*$/)) {
		alert("カタカナは半角で入力してください。");
		return false;
	}
	//生年月日
	var date = birth.split("/");
	var year = parseInt(date[0], 10);
	var month = parseInt(date[1], 10);
	var day = parseInt(date[2], 10);
	
	if(year < 1000 || year > 3000 || month == 0 || month > 12)
        return false;

    var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

    // Adjust for leap years
    if(year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)){
        monthLength[1] = 29;
    }

    // Check the range of the day
    if (!(day > 0 && day <= monthLength[month - 1])) {
		return false;
	}
	$("#fm").submit();    
}// enrollCheck

</script>

<!-- ajax!! -->

<!-- 入力画面 -->
<div style="text-align:center;">
	<form id="fm" action="/spring/search" method="post">
	<table>
		<tr>
			<td rowspan="2">ID</td>
			<td>
				<input type="text" name="id" id="id" value="${vo.id }" />
			</td>
		</tr>
		<tr>
			<td>
				<!-- ajax処理！！ -->
				<div id="idBtnDiv">
				<button type="button" id="idCheck" value="idCheck">使用できるか確認</button>
				</div>
			</td>
		</tr>
		<tr>
			<td>パスワード:</td>
			<td>
				<input type="password" id="pass" name="pass" value="" />
			</td>
		</tr>
		<tr>
			<td>パスワード再入力:</td>
			<td>
				<input type="password" id="passCheck" name="passCheck" value="" />
			</td>
		</tr>
		<tr>
			<td>名前:</td>
			<td>
			<input type="text" id="name" name="name" value="${vo.name }" />
			</td>
		</tr>
		<tr>
			<td>カナ:</td>
			<td>
			<input type="text" id="kana" name="kana" value="${vo.kana }" />
			</td>
		</tr>
		<tr>
			<td>生年月日(yyyy/mm/dd):</td>
			<td>
				<input type="text" id="birthText" value="${vo.birth }" maxlength="10" />
				<input type="hidden" name="birth" id="birth" value="${vo.birth }"/>
			</td>
		</tr>
		<tr>
			<td>委員会:</td>
			<td>
				<input type="text" name="club" value="${vo.club }" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button name="submit" id="enrollBtn" value="登録します" >登録します</button>
				<input type="submit" name="submit" value="戻る"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</body>
</html>