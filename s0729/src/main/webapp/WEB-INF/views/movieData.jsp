<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>movieData.jsp</title>
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
		<style>
		#main{width: 1600px; margin: 20px auto; text-align: center}
		#body{width: 1600px; height: 1000px; margin: 20px auto; 
		      border: 3px solid black;}
		table{width: 1400px; margin: 0 auto; }
		table, th, td{ border: 1px solid black; border-collapse: collapse;}
		th{height:40px;}
		td{height: 35px;}
		</style>
	</head>
	<body>
<script type="text/javascript">
$(function(){
	$("#btn").click(function(){
		$.ajax({
			url: "/searchMovie",
			type: "get",
			data :{"txt":$("#txt").val()},
			dataType:"json",
			success: function(data){
				alert("성공");
				console.log(data);
				var iarr = data.boxOfficeResult.dailyBoxOfficeList;
				var str = '';
				for(var i = 0 ; i < iarr.length ; i++ ){
					str+='<tr>';
					str+='<td>'+iarr[i].rank +'</td>';
					str+='<td>'+iarr[i].rankInten +'</td>';
					str+='<td>'+iarr[i].movieNm +'</td>';
					str+='<td>'+iarr[i].openDt +'</td>';
					str+='<td>'+iarr[i].salesAcc+'</td>';
					str+='<td>'+iarr[i].audiAcc +'</td>';
					str+='</tr>';
				}
				$("#content").html(str);
			},
			error: function(){
				alert("실패");
			}
		});// ajax
	});// btn
});// jquery
</script>
		<div id="main">
			<h1>영화 데이터 정보</h1>
			날짜 입력:<input type="text" name="txt" id="txt">
			<button type="button" id="btn">검색</button>
			<br><br>
			<div id = "body">
				<table>
					<colgroup>
						<col width="10%">
						<col width="25%">
						<col width="10%">
						<col width="15%">
						<col width="20%">
						<col width="20%">
					</colgroup>
					<thead>
						<tr>
							<th>순위</th>
							<th>전일대비</th>
							<th>영화제목</th>
							<th>개봉일</th>
							<th>누적매출액</th>
							<th>누적관객수</th>
						</tr>
					</thead>
					<tbody id="content">
					</tbody>
				</table>
			</div>
		
		</div>
	
	</body>
</html>