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
		console.log(data)
	    if (status === kakao.maps.services.Status.OK) {
		displayPlaces(data)
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
	        position: mposition
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
	

	// 검색 결과 목록과 마커를 표출하는 함수입니다
	function displayPlaces(places) {

	    var listEl = document.getElementById('placesList'), 
	    menuEl = document.getElementById('menu_wrap'),
	    fragment = document.createDocumentFragment(), 
	    bounds = new kakao.maps.LatLngBounds(), 
	    listStr = '';
	    
	    // 검색 결과 목록에 추가된 항목들을 제거합니다
	 //   removeAllChildNods(listEl);

	    // 지도에 표시되고 있는 마커를 제거합니다
	//    removeMarker();
	    
	    for ( var i=0; i<places.length; i++ ) {

	        // 마커를 생성하고 지도에 표시합니다
	        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
	            marker = addMarker(placePosition, i), 
	            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
	        // LatLngBounds 객체에 좌표를 추가합니다
	        bounds.extend(placePosition);

	        // 마커와 검색결과 항목에 mouseover 했을때
	        // 해당 장소에 인포윈도우에 장소명을 표시합니다
	        // mouseout 했을 때는 인포윈도우를 닫습니다
	        (function(marker, title) {
	            kakao.maps.event.addListener(marker, 'mouseover', function() {
	                displayInfowindow(marker, title);
	            });

	            kakao.maps.event.addListener(marker, 'mouseout', function() {
	                infowindow.close();
	            });

	            itemEl.onmouseover =  function () {
	                displayInfowindow(marker, title);
	            };

	            itemEl.onmouseout =  function () {
	                infowindow.close();
	            };
	        })(marker, places[i].place_name);

	        fragment.appendChild(itemEl);
	    }

	   
	}
	
	
	// 검색결과 항목을 Element로 반환하는 함수입니다
	function getListItem(index, places) {

	    var el = document.createElement('li'),
	    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
	                '<div class="info">' +
	                '   <h5>' + places.place_name + '</h5>';

	    if (places.road_address_name) {
	        itemStr += '    <span>' + places.road_address_name + '</span>' +
	                    '   <span class="jibun gray">' +  places.address_name  + '</span>';
	    } else {
	        itemStr += '    <span>' +  places.address_name  + '</span>'; 
	    }
	                 
	      itemStr += '  <span class="tel">' + places.phone  + '</span>' +
	                '</div>';           

	    el.innerHTML = itemStr;
	    el.className = 'item';

	    return el;
	}
	
	function displayPagination(pagination) {
	    var paginationEl = document.getElementById('pagination'),
	        fragment = document.createDocumentFragment(),
	        i; 

	    // 기존에 추가된 페이지번호를 삭제합니다
	    while (paginationEl.hasChildNodes()) {
	        paginationEl.removeChild (paginationEl.lastChild);
	    }

	    for (i=1; i<=pagination.last; i++) {
	        var el = document.createElement('a');
	        el.href = "#";
	        el.innerHTML = i;

	        if (i===pagination.current) {
	            el.className = 'on';
	        } else {
	            el.onclick = (function(i) {
	                return function() {
	                    pagination.gotoPage(i);
	                }
	            })(i);
	        }

	        fragment.appendChild(el);
	    }
	    paginationEl.appendChild(fragment);
	}
	
	// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
	function addMarker(position, idx, title) {
	    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
	        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
	        imgOptions =  {
	            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
	            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
	            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
	        },
	        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
	            marker = new kakao.maps.Marker({
	            position: position, // 마커의 위치
	            image: markerImage 
	        });

	    marker.setMap(map); // 지도 위에 마커를 표출합니다
	    markers.push(marker);  // 배열에 생성된 마커를 추가합니다

	    return marker;
	}

	// 지도 위에 표시되고 있는 마커를 모두 제거합니다
	function removeMarker() {
	    for ( var i = 0; i < markers.length; i++ ) {
	        markers[i].setMap(null);
	    }   
	    markers = [];
	}
	</script>
	</body>
</html>