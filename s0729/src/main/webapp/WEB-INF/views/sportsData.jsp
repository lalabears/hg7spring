<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>sportsData.jsp</title>
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
			url: "/searchSports",
			type: "get",
			data :{"txt":$("#txt").val()},
			dataType:"json",
			success: function(data){
				alert("성공");
				console.log(data);
				var iarr = data.ListPublicReservationSport.row;
				var str = '';
				for(var i = 0 ; i < iarr.length ; i++ ){
					str+='<tr>';
					str+='<td>'+iarr[i].AREANM +'</td>';
					str+='<td>'+iarr[i].PLACENM +'</td>';
					str+='<td>'+iarr[i].PAYATNM +'</td>';
					str+='<td>'+iarr[i].SVCSTATNM +'</td>';
					str+='<td><a href="'+iarr[i].SVCURL+'">링크</a></td>';
					str+='<td>'+iarr[i].TELNO +'</td>';
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
			<h1>운동장 데이터 정보</h1>
			스포츠장 입력 : <input type="text" name="txt" id="txt">
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
							<th>위치</th>
							<th>이름</th>
							<th>유료무료</th>
							<th>예약현황</th>
							<th>link</th>
							<th>tel</th>
						</tr>
					</thead>
					<tbody id="content">
					</tbody>
				</table>
			</div>
		
		</div>
	
	</body>
</html>