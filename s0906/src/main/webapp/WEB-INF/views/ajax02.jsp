<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ajax02</title>
		<style>
		button{width:200px; height:50px; margin: 50px 0 30px 0; cursor: pointer;}
		table, th, td{border: 1px solid black; border-collapse: collapse;
		width: 1100px; margin-top: 50px;
		margin: 0 auto; text-align: center;}
		th, td{height:30px;}	
		img{width: 50px;}	
		</style>
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
		$(function(){
			$("#ajaxBtn").click(function(){
				// alert("버튼1");
				$.ajax({
					url: "https://apis.data.go.kr/B551011/PhotoGalleryService1/galleryList1?serviceKey=5yOr8%2FoR%2FU6nAz5ysFlKnUQ4wayzOogsGSLvrEmu3HET2C43zlryMCB0eAutsCw9wa0xeGjc6BAdQC1YO3LT1A%3D%3D&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&arrange=A&_type=json",
					dataType: "json",
					success: function(data){
						//alert('성공');
						console.log(data);
						//console.log(data.response.body.items.item);
						//console.log(data.response.body.items.item[0]);
						let list = data.response.body.items.item; 
						console.log(list.length);
						let html='';
						for(let i = 0 ; i < list.length; i ++){
							html+='<tr>';
							html+='<td>'+list[i].galContentId+'</td>';
							html+='<td>'+list[i].galCreatedtime+'</td>';
							html+='<td>'+list[i].galPhotographer+'</td>';
							html+='<td>'+list[i].galTitle+'</td>';
							html+='<td> <img src="'+list[i].galWebImageUrl+'"></td>';
							html+='</tr>';
						}
						$("#tbody").html(html);
					},
					error: function(){
						alert('실패');
					}
				});// ajax
			});// button
			
			$("#ajaxXml").click(function(){
				//alert("버튼2");
				$.ajax({
					url: "https://apis.data.go.kr/B551011/PhotoGalleryService1/galleryList1?serviceKey=5yOr8%2FoR%2FU6nAz5ysFlKnUQ4wayzOogsGSLvrEmu3HET2C43zlryMCB0eAutsCw9wa0xeGjc6BAdQC1YO3LT1A%3D%3D&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&arrange=A&_type=xml",
					dataType: "xml",
					success: function(data){
						alert('성공');
						console.log(data);
						let list = $(data).find("item");
						console.log(list.length);
						console.log(list[0]);
						let html='';
						for(let i = 0 ; i < list.length; i ++){
							html+='<tr>';
							html+='<td>'+list.eq(i).find("galContentId").text()+'</td>';
							html+='<td>'+list.eq(i).find("galCreatedtime").text()+'</td>';
							html+='<td>'+list.eq(i).find("galPhotographer").text()+'</td>';
							html+='<td>'+list.eq(i).find("galTitle").text()+'</td>';
							html+='<td> <img src="'+list.eq(i).find("galWebImageUrl").text()+'"></td>';
							html+='</tr>';
						}
						$("#tbody").html(html);
						
						
					},
					error: function(){
						alert('실패');
					}
				});// ajax
				
			});//
			
			
		}); // jquery
		
		</script>
		
		
		
	</head>
	<body>
		<button id="ajaxBtn">공공api 연결 json</button>
		<button id="ajaxXml">공공api 연결 xml</button>
		<table>
			<tr>
				<th>번호</th>
				<th>촬영일자</th>
				<th>촬영기사</th>
				<th>제목</th>
				<th>이미지링크</th>
			</tr>
			<tbody id="tbody">
			<tr>
				<td>1</td>
				<td>20240724</td>
				<td>한국관광공사 홍길동</td>
				<td>문경새재도립공원</td>
				<td>http://.... .jpg</td>
			</tr>
			</tbody>
		</table>
	</body>
</html>