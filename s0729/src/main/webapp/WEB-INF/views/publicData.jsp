<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>publicData.jsp</title>
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
		<style>
		#main{width: 1600px; margin: 20px auto; text-align: center}
		#body{width: 1600px; height: 1000px; margin: 20px auto; 
		      border: 3px solid black;}
		table{width: 1400px; margin: 0 auto; }
		table, th, td{ border: 1px solid black; border-collapse: collapse;}
		th{height:40px;}
		td{height: 35px;}
		img{width: 50px;}
		</style>
	</head>
	<body>
<script type="text/javascript">
$(function(){
	$("#btn").click(function(){
		$.ajax({
			url: "/searchPublic",
			type: "get",
			data :{"txt":$("#txt").val()},
			dataType:"json",
			success: function(data){
				alert("성공");
				console.log(data);
				let iarr = data.response.body.items.item; 
				console.log(iarr[0]);
				var str='';
				for(var i = 0 ; i <iarr.length;i++){
					str+='<tr>';
					str+='<td>'+iarr[i].galContentId+'</td>';
					str+='<td>'+iarr[i].galTitle+'</td>';
					str+='<td>'+iarr[i].galCreatedtime+'</td>';
					str+='<td>'+iarr[i].galPhotographyLocation +'</td>';
					str+='<td>'+iarr[i].galPhotographer +'</td>';
				//	str+='<td>'+iarr[i].galWebImageUrl +'</td>';
					str+='<td><img src="'+iarr[i].galWebImageUrl +'"></td>';
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
			<h1>관광사진 데이터 정보</h1>
			<input type="text" name="txt" id="txt">
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
							<th>번호</th>
							<th>제목</th>
							<th>촬영일</th>
							<th>쵤영장소</th>
							<th>작가</th>
							<th>사진</th>
						</tr>
					</thead>
					<tbody id="content">
					</tbody>
				</table>
			</div>
		
		</div>
	
	</body>
</html>