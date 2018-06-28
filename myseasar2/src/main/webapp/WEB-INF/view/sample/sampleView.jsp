<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:te="http://www.seasar.org/teeda/extension" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Sample View</title>
</head>
<body>
<s:form method="POST" action="/sample/next">
	メッセージを
	<html:text property="message" />
	<s:submit property="next" value="OK" />
</s:form>
</body></html>