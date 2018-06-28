<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=SJIS" />
   <title> DataTransfer </title>
   <style type="text/css">
        div>div{
            display: inline-block;
            padding: 10px;
            background-color: #aaa;
            margin: 3px;
        }
    </style>
</head>
<body>
<div style="width:600px;border:1px solid black;">
<h2>好きな項目をお気に入りへ移動</h2>
<div draggable="true" ondragstart="dsHandler(event);">google</div>
<div draggable="true" ondragstart="dsHandler(event);">yahoo</div>
<div draggable="true" ondragstart="dsHandler(event);">MS</div>
<div draggable="true" ondragstart="dsHandler(event);">apple</div>
</div>
<div id="dest"
    style="width:400px;height:260px; 
  border:1px solid black;float:left;">
    <h2 ondragleave="return false;">お気に入り</h2>
</div>
<img id="gb" draggable="false" src="garbagebin.png"
    alt="ゴミ箱" style="float:left;"/>
<script type="text/javascript">
   var dest = document.getElementById("dest");
   // ドラッグ開始イベント
   var dsHandler = function(evt)
   {
       // innerHTMLを運ぶ
       evt.dataTransfer.setData("text/plain"
           , "<item>" + evt.target.innerHTML);
   }
   dest.ondrop = function(evt)
   {
       var text = evt.dataTransfer.getData("text/plain");
       // <item>開始するデータなら
       if (text.indexOf("<item>") == 0)
       {
           // DIV要素生成
           var newEle = document.createElement("div");
           // ID生成
           newEle.id = new Date().getUTCMilliseconds();
           // 運ばれたデータ
           newEle.innerHTML = text.substring(6);
           // ドラッグ可能
           newEle.draggable="true";
           // ドラッグ開始イベント
           newEle.ondragstart = function(evt)
           {
               // <remove>データを運ぶ
               evt.dataTransfer.setData("text/plain"
                   , "<remove>" + newEle.id);
           }
           dest.appendChild(newEle);
       }

   }
   // ゴミ箱
   document.getElementById("gb").ondrop = function(evt)
   {
       var id = evt.dataTransfer.getData("text/plain");
       // <remove>開始するデータなら
       if (id.indexOf("<remove>") == 0)
       {
           // 要素取得
           var target = document.getElementById(id.substring(8));
           // 要素削除
           dest.removeChild(target);
       }
   }
   document.ondragover = function(evt)
   {
       // デフォルト操作取り消し
       return false;
   }
   document.ondrop = function(evt)
   {
       // デフォルト操作取り消し
       return false;
   }
</script>
</body>
</html>
