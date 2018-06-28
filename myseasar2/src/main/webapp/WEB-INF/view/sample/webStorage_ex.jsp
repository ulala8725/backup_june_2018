<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:te="http://www.seasar.org/teeda/extension" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="resources/jquery-3.3.1.js"></script>
<title>web storage example</title>
<style type="text/css">
	table{
		border-collapse: collapse;	
	}
	
	td, th{
		border: 1px solid #888;
		padding: 4px;
	}
</style>
</head>
<body>
  <h2>掲示板</h2>
  <textarea id="msg" name="msg" cols="50" rows="5"></textarea><br/>
  <input type="button" value="保存" onclick="addMsg();" />
  <input type="button" value="全削除" onclick="clearMsg();" />
  <hr/>
  <table style="width:550px">
      <tr>
          <th>message</th>
          <th>time</th>
      </tr>
      <tbody id="show"></tbody>
  </table>
  <script type="text/javascript">
      // message読取
      var loadMsg = function() {
              var tb = document.getElementById("show");
              tb.innerHTML = "";

              for (var i = 0; i < localStorage.length; i++) {
                  var key = localStorage.key(i);
                  var date = new Date();
                  date.setTime(key); // key: time, value: msg
                  // time
                  var datestr = date.toLocaleDateString() + "&nbsp;" + date.toLocaleTimeString();
                  // message
                  var value = localStorage.getItem(key);
                  var row = tb.insertRow(i);
                  // セル挿入
                  row.insertCell(0).innerHTML = value;
                  // セル挿入
                  row.insertCell(1).innerHTML = datestr;
              }
          }
          // message追加
      var addMsg = function() {
          var msgElement = document.getElementById("msg");
          var time = new Date().getTime();
          localStorage.setItem(time, msgElement.value);
          msgElement.value = "";

          loadMsg();
      }

      // message削除
      function clearMsg() {
          localStorage.clear();
          loadMsg();
      }
      window.onload = loadMsg();
  </script>
</body>
</html>