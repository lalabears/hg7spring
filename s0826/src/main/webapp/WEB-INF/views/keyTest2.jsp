<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
function enterKey() {
		// 엔터를 누르면 전송 됩니다.
	if (event.keyCode === 13) {
		//alert('전송');
		location.href="/"
		location.replace("/")
		location.assign("/")
	}
}
/*
$(function(){
	
	$('#in01').keypress(function (e) {
		//   keypress, keyup, keydown 다 됨
		console.log(e.keyCode)
		if(e.keyCode == 13) {
	        console.log('엔터!')
	    }
	})
});*/
</script>

</head>
<body>
<input type="text" id="in01" onkeydown="enterKey()">

</body>
</html>