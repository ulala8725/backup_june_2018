<%@page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>readBoard</title>
	<script src="/spring/resources/jquery-3.3.1.js"></script>
</head>
<body>
<script>
$(function(){
	$("#modifyBtn").on("click", modifyCheck);
	$("#deleteBtn").on("click", deleteConfirm);
	$("#returnBtn").on("click", returnBoard);
	$("#reloadBtn").on("click", reloadBoard);
	$(document).on("keydown", event, reloadBoard);
	$(document).on("click", "#commentBtn", writeComments);
	$(document).on("click", ".reModifyBtn", modifyReply);
	$(document).on("click", ".reModifyConfirmBtn", modifyReplyConfirm);
	$(document).on("click", ".reCancelBtn", cancelModifyReply);
	$(document).on("click", ".reDeleteBtn", deleteReply);
});

function modifyCheck(){
	if (confirm("修正しますか。")) {
		$("#title").attr("readonly", false);
		$("#contents").attr("readonly", false);
		$("#message").text("${sessionScope.loginId }さん、修正しています。")
		$("#modifyBtn").text("確認");
		$("#modifyBtn").off("click"); // 既存ボタンのイベントoffにする。
		$("#modifyBtn").attr("id", "confirmBtn");
		$("#confirmBtn").attr("onclick", "modifyConfirm()"); // 替わったボタンにイベントを追加する。
		$("#deleteBtn").off("click"); // 既存ボタンのイベントoffにする。
		$("#deleteBtn").hide(); // ボタンを隠す。
	}
}

function modifyConfirm(){
	if (confirm("修正しますか。")) {
		$("#fm").submit();
	}
}

function deleteConfirm(){
	if (confirm("削除しますか。")) {
		location.href = "deleteBoard?no=${vo.no}";
	}
}

function returnBoard(){
	location.href = "gotoboard";
}

function reloadBoard(event){
	if (event.keyCode == 116) { //F5
		location.href="/spring/board/readBoard?no=${vo.no }"; // location.reload();
		return true;
	} return false;
}

function writeComments(){
	if ($("#comments").val() == "") {
		alert("コメントを書いてください。");
		return false;
	}
	if (confirm("コメントを書きますか")) {
		$("#replyFm").submit();
		return true;
	}
}

function modifyReply(){
	var target = $(this).parent().siblings().children(".replyComments");
	target.attr("readonly", false);
	target.css("background-color", "yellow");
	target.focus();
	$(this).text("確認");
	$(".reModifyBtn").off("click");
	$(this).attr("class", "reModifyConfirmBtn");
	$(this).siblings(".reDeleteBtn").text("取り消し");
	$(this).siblings(".reDeleteBtn").attr("class", "reCancelBtn");
	return false;
}

function modifyReplyConfirm(){
	var target = $(this).parent().siblings().children(".replyComments");
	var replyNo = $(this).siblings(".replyNo").val(); 
	if (target.val() == "") {
		alert("コメントを書いてください。");
		return false;
	}
	
	if (confirm("コメントを修正しましか。")) {
		$.ajax({
			url: "/spring/reply/modifyReply",
			type: "post",
			data: {
				"no":replyNo,
				"comments":target.val(),
			},
			success: function(result){
				if (result == 1) {
					alert("コメントの修正に成功しました。")
				} else {
					alert("コメントの修正に失敗しました。")
				}
			},
			error: function(){}
		});// ajax
	}// if
	
	target.attr("readonly", true);
	target.css("background-color", "");
	$(this).text("修正");
	$(this).attr("class", "reModifyBtn");
	$(".reModifyBtn").on("click");
	$(this).siblings(".reCancelBtn").attr("class", "reDeleteBtn");
	$(this).siblings(".reDeleteBtn").text("削除");
	$("#replyFm").attr("action", ""); // formのwriteReplyの実行を切る。
}

function cancelModifyReply(){
	$("#replyFm").attr("action", ""); // formのwriteReplyの実行を切る。
	location.href = "/spring/board/readBoard?no=${vo.no}";
}

function deleteReply(){
	if (confirm("本当に削除しますか。")) {
		var replyNo = $(this).siblings(".replyNo").val();
		$.ajax({
			url: "/spring/reply/deleteReply",
			type: "post",
			data: {
				"no":replyNo,
			},
			success: function(result){
				if (result == 1) {
					alert("コメントの削除に成功しました。")
				} else {
					alert("コメントの削除に失敗しました。")
				}
			},
			error: function(){}
		});// ajax
	}
	$("#replyFm").attr("action", "");// formのwriteReplyの実行を切る。
}

function downloadFile(){
	if (confirm("ファイルをダウンロードしますか。")) {
		location.href = "/spring/board/downloadFile?no=${vo.no }";
	}
}

</script>

<!-- id表示 -->
<p id="message">${sessionScope.loginId }さん、読んでいます。</p>
${requestScope.message }
<br>

<div>
<form id="fm" action="modifyBoard" method="post">
<table border="1">
	<tr>
		<th>no.</th>
		<td><input type="text" name="no" value="${vo.no }" readonly/></td>
	</tr>
	<tr>
		<th>作成者</th>
		<td>${vo.id }</td>
	</tr>
	<tr>
		<th>作成日付</th>
		<td>${vo.write_date }</td>
	</tr>
	<tr>
		<th>ヒット数</th>
		<td>${vo.hit }</td>
	</tr>
	<tr>
		<th>タイトル</th>
		<td><input type="text" id="title" name="title" value="${vo.title }" readonly/></td>
	</tr>
	<tr>
		<th>添付ファイル</th>
		<td><a href="javascript:downloadFile()" >${vo.save_file }</a></td> 
	</tr>
	<tr height="20px">
		<th>内容</th>
		<td><input type="text" id="contents" name="contents" value="${vo.contents }" readonly/></td>
	</tr>
</table>
</form>
<c:if test="${sessionScope.loginId == vo.id }">
<button id="modifyBtn">修正</button>
<button id="deleteBtn">削除</button>
</c:if>
<button id="returnBtn">戻る</button>
<!-- <button id="reloadBtn">reload</button> -->
</div>
<br>
<div>
	<form id="replyFm" action="/spring/reply/writeReply" method="post">
	<table id="replyTable">
		<tr>
			<td colspan='3'><input type='text' id='comments' name='comments' placeholder='コメントを書いてください。'/></td>
 			<td><button id='commentBtn' name='submit'>コメント登録</button></td></tr>
			
 			<c:forEach items="${replyVo}" var="replyVo">
		<tr>
 				<td><input type='text' class='replyComments' name='replyComments' value='${replyVo.comments }' readonly/></td>
 				<td><input type='text' value='${replyVo.id }' readonly/></td>
 				<td><input type='text' value='${replyVo.write_date }' readonly/></td>
 				<td>
 				<c:if test="${sessionScope.loginId == replyVo.id }">
 				<button class='reModifyBtn'>修正</button>
 				<button class='reDeleteBtn'>削除</button>
 				</c:if>
 				<input type='hidden'  name='no' value='${replyVo.no }' />
 				</td>
		</tr>
 			</c:forEach>
 			<c:if test="${replyNavi == null }"></c:if>
 			<c:if test=""></c:if>
		<tr>
			<td colspan='5' align='center' id='replyTablePage'>
				<a href='/spring/board/readBoard?no=${vo.no}&currentPage=${replyNavi.currentPage - replyNavi.pagePerGroup}'>最初</a>
				<a href='/spring/board/readBoard?no=${vo.no}&currentPage=${replyNavi.currentPage - 1}'>前に</a>
				<c:forEach begin='${replyNavi.startPageGroup}' end='${replyNavi.endPageGroup}' var='counter'>
				<c:if test='${counter == replyNavi.currentPage}'><b></c:if>
				<a href='/spring/board/readBoard?no=${vo.no}&currentPage=${counter }'>${counter }</a>
				<c:if test='${counter == replyNavi.currentPage}'></b></c:if>
				</c:forEach>
				<a href='/spring/board/readBoard?no=${vo.no}&currentPage=${replyNavi.currentPage + 1}'>次に</a>
				<a href='/spring/board/readBoard?no=${vo.no}&currentPage=${replyNavi.currentPage + replyNavi.pagePerGroup}'>最後</a>
			</td>
		</tr>
			
	</table>
	<input type="hidden" name="boardNo" value="${vo.no }" />
<%-- 	<input type="hidden" name="id" value="${sessionScope.loginId }" /> --%>
	</form>
</div>
</body>
</html>
