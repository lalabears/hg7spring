<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>map1.jsp</title>
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d99aad64257f4c7b6af58515d60b1a79&libraries=services,clusterer,drawing"></script>
		<script>
		
		
		</script>
	</head>
	<body>
	키워드 : <input type="text" id="keyword" value="이태원 맛집">
	<button type="button" onclick="sBtn()">이동</button>
	<br><br>
	<!-- 지도를 표시할 div 입니다 -->
	<div id="map" style="width:100%;height:350px;"></div>
	<div id="route" style="width:100%;height:350px;">
		
	<table>
	<thead>
		<tr>
			<th>이름</th>
			<th>주소</th>
			<th>전화번호</th>
			<th>버튼</th>
		</tr>
	</thead>
	<tbody id ="tbody">
	</tbody>
	</table>
	
	
	<script>
	let userLoc ="21160570"
	let map;
	let markers = [];
	let coffeIm = 'img/coffee2.png'
	
	
	var imageSize = new kakao.maps.Size(22, 26)
    var imageOptions = {  
            spriteOrigin: new kakao.maps.Point(10, 0),    
            spriteSize: new kakao.maps.Size(36, 98)  
        };     
	var markerImage = 
		createMarkerImage(coffeIm, imageSize, imageOptions)    
	
	function createMarkerImage(src, size, options) {
    	var markerImage = new kakao.maps.MarkerImage(src, size, options);
    	return markerImage;            
	}
	drawMap(37.5658049, 126.9751461)
	function drawMap(x,y){
		console.log(x,y)
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = { 
	        center: new kakao.maps.LatLng(x, y), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };
		// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
		map = new kakao.maps.Map(mapContainer, mapOption); 
	}
// 키워드로 검색하기 			
	function sBtn(){
		var keyword = $("#keyword").val();
		console.log(keyword)
		
		// 지도를 표시할 div 
		var mapContainer = document.getElementById('map'), 
		    mapOption = {
		        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
		        level: 1 // 지도의 확대 레벨
		    };  
		// 지도를 생성합니다    
		map = new kakao.maps.Map(mapContainer, mapOption); 
		// 장소 검색 객체를 생성합니다
		var ps = new kakao.maps.services.Places(); 
		// 키워드로 장소를 검색합니다
		ps.keywordSearch(keyword, placesSearchCB); 
		
	}

	// 키워드 검색 완료 시 호출되는 콜백함수 입니다
	function placesSearchCB (data, status, pagination) {	
		console.log("data",data)
	    if (status === kakao.maps.services.Status.OK) {
	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
	        // LatLngBounds 객체에 좌표를 추가합니다
	        var bounds = new kakao.maps.LatLngBounds();
	        for (var i=0; i<data.length; i++) {
	            displayMarker(data[i]);    
	            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
	            markers.push(data[i])
	        }       
	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
	        map.setBounds(bounds);
	    } 
	}
	// 지도에 마커를 표시하는 함수입니다
	function displayMarker(place) {
		console.log(place)
	    // 마커를 생성하고 지도에 표시합니다
	    var mposition = new kakao.maps.LatLng(place.y, place.x);
	    var marker = new kakao.maps.Marker({
	        map: map,
	        position: mposition,
	        image: markerImage
	    });
	    showTable();
	    
	    
		var iwContent = '<div style="padding:5px;">'+place.place_name+'<br>'+
						'<button onclick="ShowMap()">지도보기</button>'+
						'<button onclick="showRoute('+place.id+')">길찾기</button>'
	//	'<a href="https://map.kakao.com/link/map/'+place.id+'" style="color:blue" target="_blank">큰지도보기</a>'+ 
	//	'<a href="https://map.kakao.com/link/from/'+userLoc+'/to/'+place.id+'" style="color:blue" target="_blank">길찾기</a></div>'
		
		
	    var infowindow = new kakao.maps.InfoWindow({
	        content : iwContent,
	        position : mposition,
	        removable : true
	    });	    
	    // 마커에 클릭이벤트를 등록합니다
	    kakao.maps.event.addListener(marker, 'click', function() {
	    	console.log(infowindow.getPosition())
	    	console.log(marker.getPosition())
		    infowindow.open(map, marker);
	    });
	}
	
	function showTable(){
		let str ='';
		for(var i=0;i<markers.length;i++){
			str+='<tr>';
			str+='<td>'+markers[i].place_name+'</td>';
			str+='<td>'+markers[i].address_name+'</td>';
			str+='<td>'+markers[i].phone+'</td>';
			str+='<td><button>버튼</button></td>'
			str+='</tr>'
		}
		$("#tbody").html(str)
	}

	function showRoute(pid){
	//	<iframe style="width:100%;height:350px;"  
	//		src="https://map.kakao.com/link/from/21160570/to/1796473441">
	// </iframe>
		//	'<a href="https://map.kakao.com/link/from/'+userLoc+'/to/'+place.id+'" style="color:blue" target="_blank">길찾기</a></div>'

		var str ='<iframe style="width:100%;height:350px;"  src="'+'https://map.kakao.com/link/from/'+userLoc+'/to/'+pid+'+"></iframe>'; 
	
		$("#route").html(str);
	}
	
	
	</script>
	</body>
</html>