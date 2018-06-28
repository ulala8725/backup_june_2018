<%@page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>検索画面</title>
<script src="/spring/resources/jquery-3.3.1.js"></script>
</head>
<body>
<script>
	$(function(){
		$("#searchBtn").on("click", searchUser);
	});
	
	function searchUser(){
		var id = $("#id").val();
		var name = $("#name").val();
		var kana = $("#kana").val();
		
		if (id == "" || name == "" || kana == "") {
			alert("全項目を入力してください。");
			return false;
		}
		//半角英数字
		if (!id.match(/^[0-9a-zA-Z]+$/)) {
			alert("idは半角英数字だけ入力してください。");
			return false;
		}
		//全角
		if (!name.match(/^[^\x01-\x7E\xA1-\xDF]+$/)) {
			alert("名前は全角で入力してください。");
			return false;
		}
		//半角
		if (!kana.match(/^[\x20-\x7e]*$/)) {
			alert("カタカナは半角で入力してください。");
			return false;
		}
		
		$.ajax({
			url:"/spring/search/search",
            type:"post",
            dataType:"json",
            data: {"id":$("#id").val(),
            		"name":$("#name").val(),
            		"kana":$("#kana").val()
            		},
            success: function(result){
            	$(result).each(function(index, item){
	            	$("#resultTable").html("");
	        		$("#resultTable").append("<table border='1' align='center' id='tb'></table>")
	        		$("#tb").append("<tr><td>ID</td><td>名前</td><td>カナ</td><td>生年月日</td><td>委員会</td>"
	            			+"<td align='justify' colspan='2'>操作</td></tr>");
            		$("#tb").append("<tr><td>"+item.id+"</td><td>"+item.name+"</td><td>"+item.kana
            				+"</td><td>"+item.birth+"</td><td>"+item.club+"</td><td>"
            				+"<input type='submit' name='submit' value='更新'/><input type='submit' name='submit' value='削除'/>"
            				+"</td></tr>")
	            	})// each
            },// success
            error: function(){ // return == null
            	$("#resultTable").html("");
            	$("#resultTable").append("<tr><td colspan='6'>検索結果がありません。</td></tr></table>")
            }
		});// ajax
	}// searchUser
	
</script>

<!-- id表示 -->
${sessionScope.loginId }さん、ようこそ！<br>
${requestScope.message }<br>

<!-- 検索条件 -->
<div style="text-align:right;">
	<form action="board" method="post">
		<input type="submit" name="submit" value="掲示板" />
<!-- 		<a href="board">掲示板</a> get -->
	</form>
</div>
<div style="text-align:center;">
	<p style="font-size:20px;">※前一致で検索します</p>
	<p style="font-size:35px; font-weight:bold;">検索画面</p>
	
	<form action="search" method="post">
		<table border="1" align="center">
			<tr>
				<td>ID</td>
				<td colspan="3"><input type="text" name="id" id="id" value="${vo.id }" /></td>
			</tr>
			<tr>
				<td>名前</td>
				<td colspan="3"><input type="text" id="name" name="name" value="${vo.name }" /></td>
			</tr>
			<tr>
				<td>カナ</td>
				<td colspan="3"><input type="text" id="kana" name="kana" value="${vo.kana }" /></td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="button" name="submit" value="検索" id="searchBtn" />
					<input type="submit" name="submit" value="新規登録" />
					<input type="submit" name="submit" value="ログアウト" />
				</td>
			</tr>
		</table>
	
<br>
<div id="resultTable"></div>
<%-- <c:if test="${resultVo ne null}"> --%>
<!-- 検索結果　 --> 
<!-- 		<table border="1" align="center"> -->
<!-- 			<tr> -->
<!-- 				<td>ID</td> -->
<!-- 				<td>名前</td> -->
<!-- 				<td>カナ</td> -->
<!-- 				<td>生年月日</td> -->
<!-- 				<td>委員会</td> -->
<!-- 				<td align="justify" colspan="2">操作</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<%-- 				<td>${resultVo.id }</td> --%>
<%-- 				<td>${resultVo.name }</td> --%>
<%-- 				<td>${resultVo.kana }</td> --%>
<%-- 				<td>${resultVo.birth }</td> --%>
<%-- 				<td>${resultVo.club }</td> --%>
<!-- 				<td> -->
<!-- 					<input type="submit" name="submit" value="更新" /> -->
<%-- 					<input type="hidden" name="list" value='<bean:write name="list"/>'> --%>
<!-- 					<input type="submit" name="submit" value="削除" /> -->
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
<%-- </c:if> --%>
</form>
</div>	
	
</body>
</html>