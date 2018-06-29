<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:te="http://www.seasar.org/teeda/extension" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>html5's new attribute of a form tag</title>
</head>
<body>
<form id="addForm" action="http://www.google.com" >
<label for="phLable">placeholder attribute</label>
<input type="text" name="name" id="phLable" placeholder="name" autocomplete /><br>
<label for="listLable">list attribute</label>
<input type="text" name="name" id="listLable" list="books" /><br>
<datalist id="books">
	<option value="java">java</option>
	<option value="html5">html5</option>
	<option value="spring">spring</option>
</datalist>
<input type="submit" value="通常submit"/><br>
    <input type="submit" value="formaction属性(yahoo.co.jp)" 
    	formaction="http://yahoo.co.jp"/><br>
    <input type="submit" value="formaction属性(javait.hatenablog.com)" 
    	formaction="http://javait.hatenablog.com"/><br>
    <input type="submit" value="formmethod属性(GETメソッド)" formmethod="get"/><br>
    <input type="submit" value="formmethod属性(POSTメソッド)" formmethod="post"/><br>
</form>
<label for="taLabel">form属性&autofocus属性</label>
<textarea name="textarea" form="addForm" id="taLabel" style="resize:none;" autofocus></textarea>
<hr>
output example
<form oninput="result.value=parseInt(a.value)+parseInt(b.value)">
    <input type="range" name="b" value="50" /> +
    <input type="number" name="a" value="10" /> =
    <output name="result" for="a b">60</output>
</form>
<form action="add">
    book(必須)<input name="name" type="text" required/><br/>
    isbn(必須 000-000形式)<input name="isbn" type="text" required pattern="\d{3}-\d{3}"/><br/>
    price(最小20 最大150 5の倍数)<input name="price" type="number" 
        min="20" max="150" step="5"/><br/>
    <input type="submit" value="send"/>
</form>


</body></html>