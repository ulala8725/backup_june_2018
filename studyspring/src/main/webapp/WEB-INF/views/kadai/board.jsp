<%@page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>board</title>
	<script src="/spring/resources/jquery-3.3.1.js"></script>
</head>
<body>
<script>
$(function(){
	$("#returnBtn").on("click", returnSearch);
	
});
	
function returnSearch(){
	location.href = "/spring/search";
}
</script>

<!-- id表示 -->
<p>${sessionScope.loginId }さん、掲示板です。</p>
${message }
<br> 

<a href="/spring/board/gotowrite" >書く<img src="/spring/resources/image/image1.jpg" width="50" height="50" /></a>
<table border="1">
	<tr>
		<th>No.</th>
		<th>タイトル</th>
		<th>作成者</th>
		<th>作成日付</th>
		<th>ヒット数</th>
	</tr>
	<c:if test="${list == null }">
	<tr>
		<td colspan="5" style="text-align:center;">リストがありません。</td>
	</tr>
	</c:if>
	<c:if test="${list != null }">
	<c:forEach items="${list }" var="vo" >
		<tr>
			<td>${vo.no }</td>
			<td><a href="/spring/board/readBoard?no=${vo.no }&startFrom=board">${vo.title }</a></td>
			<td>${vo.id }</td>
			<td>${vo.write_date }</td>
			<td>${vo.hit }</td>
		</tr>
	</c:forEach>
	</c:if>
		<tr>
			<td colspan="5" align="center">
				<a href="/spring/board/gotoboard?currentPage=${navi.currentPage - navi.pagePerGroup }">最初</a>
				<a href="/spring/board/gotoboard?currentPage=${navi.currentPage - 1 }">前に</a>
				<c:forEach begin="${navi.startPageGroup }" end="${navi.endPageGroup }" var="counter">
				<c:if test="${counter == navi.currentPage }"><b></c:if>
				<a href="/spring/board/gotoboard?currentPage=${counter }">${counter }</a>
				<c:if test="${counter == navi.currentPage }"></b></c:if>
				</c:forEach>
				<a href="/spring/board/gotoboard?currentPage=${navi.currentPage + 1 }">次に</a>
				<a href="/spring/board/gotoboard?currentPage=${navi.currentPage + navi.pagePerGroup }">最後</a>
			</td>
		</tr>
</table>
<button id="returnBtn">戻る</button>
</body>
</html>
