<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>사전 페이지</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(function(){
		if($("#category").val() == "none"){
			$("#categoryDetail").html('<option value = "none"></option>');
		}else if($("#category").val() == "disease"){
			let optionHtml = "";
			optionHtml += '<option value = "sickCd">질병코드</option>';
			optionHtml += '<option value = "sickNm">질병명</option>';
			$("#categoryDetail").html(optionHtml);
		}else if($("#category").val() == "medicine"){
			let optionHtml = "";
			optionHtml += '<option value = "entpName">회사명</option>';
			optionHtml += '<option value = "itemName">제품명</option>';
			optionHtml += '<option value = "efcyQesitm">효능</option>';
			optionHtml += '<option value = "atpn">주의사항</option>';
			$("#categoryDetail").html(optionHtml);
		}
		
		$('#textBox').keypress(function (e) {
			//   keypress, keyup, keydown 다 됨
			console.log(e.keyCode)
			if(e.keyCode == 13) {
		        console.log('엔터!')
		        location.href="/"
		    }
		})
		
	})
	
	
/*	function enterKey(){
	
		if (event.keyCode === 13) {
			alert()
		location.assign("/")//"keyTest?category=${category}&textBox="+$("#textBox").val()+"&categoryDetail="+$("#categoryDetail").val()
		}
	}
*/	
</script>



</head>
<body>

	<section style="height: 600px; margin: 150px">

		<h1 style="margin-top: 40px; margin-bottom: 50px; font-size: 40px; left: 50%;">질병
			및 의약품 사전</h1>
		<div class="wrapper">
			<form action="" name="search" method="post">
				<select name="category" id="category" onchange="doSelectBig()"
					style="position: absolute; top: 50%; left: 46.9%; width: 80px; height: 38px; margin-left: -180px; margin-top: -20px; padding: 5px; border: 1px solid #666666; font-family: inherit; background: url(https://www.midashotel.co.kr/Midas_common/images/homepage/board/search-box-select.png) no-repeat 95% 50%; -webkit-appearance: none; -moz-appearance: none; appearance: none;">
					<option value="disease" <c:if test="${category == 'disease' }">selected</c:if>>질병</option>
					<option value="medicine" <c:if test="${category == 'medicine' }">selected</c:if>>의약품</option>
				</select>
				<select name="categoryDetail" id="categoryDetail"
					style="position: absolute; top: 50%; left: 52.5%; width: 120px; height: 38px; /* margin-left: -185px; */ margin-top: -20px; padding: 5px; border: 1px solid #666666; font-family: inherit; background: url(https://www.midashotel.co.kr/Midas_common/images/homepage/board/search-box-select.png) no-repeat 95% 50%; -webkit-appearance: none; -moz-appearance: none; appearance: none;">
					<option value="none" <%-- <c:if test="${category == 'disease' }">selected</c:if> --%>></option>
				</select>

				<div class="title" style = "left: 55%">
					<input type="text" size="16" id="textBox" onkeydown="enterKey()">
				</div>

				<button type="button" onclick = "searchBtn()" style = "height: 38px; left: 55%">
					<i class="fas fa-search"></i>
				</button>
			</form>
		</div>
		
		
	
</body>
</html>