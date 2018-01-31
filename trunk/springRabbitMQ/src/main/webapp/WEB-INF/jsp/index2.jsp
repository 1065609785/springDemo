<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">

var websocket;

// é¦åå¤æ­æ¯å¦ æ¯æ WebSocket
 if('WebSocket' in window) {
     websocket = new WebSocket("ws://192.168.31.205:8085/springTest/testHandler?userId=zhaoshouyun1");
 } else if('MozWebSocket' in window) {
     websocket = new MozWebSocket("ws://192.168.31.205:8085/springTest/testHandler?userId=zhaoshouyun1");
 } else {
     websocket = new SockJS("http://192.168.31.205:8085/springTest/socketJs/testHandler?userId=zhaoshouyun1");
 }

 // æå¼è¿æ¥æ¶
 websocket.onopen = function(evnt) {
     console.log("  websocket.onopen  ");
 };

 // æ¶å°æ¶æ¯æ¶
 websocket.onmessage = function(evnt) {
    // alert(evnt.data);
	 var para=document.createElement("p");
		var node=document.createTextNode(evnt.data);
		para.appendChild(node);
		var div = document.getElementById('dive')
		div.appendChild(para);
 };

 websocket.onerror = function(evnt) {
     console.log("  websocket.onerror  ");
 };

 websocket.onclose = function(evnt) {
     console.log("  websocket.onclose  ");
 };


function say(){
	 //å®¢æ·ç«¯ä¸»å¨åæ¶æ¯
	 websocket.send(document.getElementById('msg').value);
}

</script>
</head>
<body>
<input type="text" value="" id="msg"><button onclick="say()"></button>
<div id="dive">

</div>
</body>
</html>