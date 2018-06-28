<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>変更入力画面</title>
<script src="/spring/resources/jquery-3.3.1.js"></script>
</head>
<body>
<script type="text/javascript">
	$(function() {
	 	$(document).on("keyup", "#birthText", inputBirth);
	 	$(document).on("click", "#modifyBtn", modifyCheck);
	});

	function inputBirth(){
		var birthText = $("#birthText").val();
		if (birthText.length == 4 || birthText.length == 7) {
			$("#birthText").val(birthText + "-") ;
		}
		$("#birth").val(birthText);
	}
	
	function modifyCheck(){
		var name = $("#name").val();
		var kana = $("#kana").val();
		var birth = $("#birth").val();
		var club = $("#club").val();
		//空白チェック
		if (name == "" || kana == "" || birth == "" || club == "") {
			alert("全項目を入力してください。");
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
		var date = birth.split("-");
		var year = parseInt(date[0], 10);
		var month = parseInt(date[1], 10);
		var day = parseInt(date[2], 10);
		
		if(year < 1000 || year > 3000 || month == 0 || month > 12){
			alert("生年月日を確認してください。(yyyy-mm-dd)");
	        return false;
		}
	    var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

	    // Adjust for leap years
	    if(year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)){
	        monthLength[1] = 29;
	    }

	    // Check the range of the day
	    if (!(day > 0 && day <= monthLength[month - 1])) {
	    	alert("生年月日を確認してください。(yyyy-mm-dd)");
			return false;
		}
		$("#fm").submit();
	}
	
</script>
<div align="center">
	<p style="font-size:8">【データ変更入力】</p>
	<form id="fm" action="/spring/search" method="post">
	<table border="1">
		<tr>
			<td>ユーザID</td>
			<td><input type="text" class="text" name="id" value="${vo.id }" readonly="readonly" /></td>
		</tr>
		<tr>
			<td>名前</td>
			<td><input type="text" class="text" id="name" name="name" value="${vo.name }" /></td>
		</tr>
		<tr>
			<td>カナ</td>
			<td><input type="text" class="text" id="kana" name="kana" value="${vo.kana }" /></td>
		</tr>
		<tr>
			<td>生年月日</td>
			<td><input type="text" class="text" id="birthText" value="${vo.birth }" maxlength="10" /> 
				<input type="hidden" name="birth" id="birth" value="${vo.birth }" />
			</td>
		</tr>
		<tr>
			<td>委員会</td>
			<td><input type="text" class="text" id="club" name="club" value="${vo.club }" /></td>
		</tr>
		<tr>
			<td colspan="2">
			<button name="submit" id="modifyBtn" value="更新確認" >確認</button>
			<input type="submit" name="submit" value="戻る" />
			</td>
		</tr>
	</table>
	</form>
</div>
	
</body>
</html>