<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>map1.jsp</title>
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d99aad64257f4c7b6af58515d60b1a79"></script>
		<script>
		/* $(function(){
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			    mapOption = { 
			        center: new kakao.maps.LatLng(37.5658049, 126.9751461), // 지도의 중심좌표
			        level: 3 // 지도의 확대 레벨
			    };
			// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
			var map = new kakao.maps.Map(mapContainer, mapOption); 
			//마커가 표시될 위치입니다 
			var markerPosition  = new kakao.maps.LatLng(37.5658049, 126.9751461); 
			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
			    position: markerPosition
			});
			
			// 마커가 지도 위에 표시되도록 설정합니다
			marker.setMap(map);
		}); //jquery */
		function sBtn(){
			var x = $("#x").val();
			var y = $("#y").val();
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = { 
		        center: new kakao.maps.LatLng(x, y), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };
			// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
			var map = new kakao.maps.Map(mapContainer, mapOption); 
			//마커가 표시될 위치입니다 
			var markerPosition  = new kakao.maps.LatLng(x, y); 
			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
			    position: markerPosition
			});
			
			// 마커가 지도 위에 표시되도록 설정합니다
			marker.setMap(map);
		
		
		
		}
		</script>
	</head>
	<body>
	x: <input type="text" id="x">
	y: <input type="text" id="y">
	<button type="button" onclick="sBtn()">이동</button>
	<br><br>
	<!-- 지도를 표시할 div 입니다 -->
	<div id="map" style="width:100%;height:350px;"></div>
	
	</body>
</html>