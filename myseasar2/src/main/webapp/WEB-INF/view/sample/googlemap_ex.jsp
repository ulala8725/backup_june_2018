<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:te="http://www.seasar.org/teeda/extension" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title> google map example</title>
<style type="text/css">
       html {
           height: 100%
       }
       body {
           height: 100%;
           margin: 0px;
           padding: 0px
       }
       #map_canvas {
           height: 100%
       }
   </style>
   <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false">
   </script>
   <script type="text/javascript">
       function initialize() {
           // geolocation属性
           navigator.geolocation.getCurrentPosition(function(position) {
                   // ブラウザの位置
                   var latlng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
                   // googlemapのオプション
                   var myOptions = {
                       zoom: 16,
                       center: latlng,
                       mapTypeId: google.maps.MapTypeId.ROADMAP
                   };
                   // 要素取得
                   var mapDiv = document.getElementById("map_canvas");
                   // 要素にmap表示
                   var map = new google.maps.Map(mapDiv, myOptions);
                   // マーク
                   var marker = new google.maps.Marker({
                       position: latlng,
                       animation: google.maps.Animation.BOUNCE,
                       map: map
                   });
                   // 表示文字
                   var info = new google.maps.InfoWindow({
                       content: "ココにあるよ"
                   });
                   // 表示
                   info.open(map, marker);
               },
               function(error) {
                   alert("位置取得できませんでした。");
               }, {
                   enableHighAccuracy: true,
                   maximumAge: 1000
               });
       }
   </script>
</head>
<body onload="initialize()">
    <div id="map_canvas" style="width:50%; height:50%"></div>
</body>
</html>