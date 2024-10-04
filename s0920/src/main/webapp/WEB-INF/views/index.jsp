<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
		<style type="text/css">
		table, tr, td{border: 1px solid black; border-collapse: collapse;}
		tr, td{width: 100px; height: 10px;}
		</style>
		
	
		
	</head>
	<body>
	<h3><a href="login">로그인</a></h3>
	<h3>${sessionId } 님 환영합니다</h3>
	
	<h3><a href="/kakao/message">message</a></h3>
	<h3><a href="/kakao/friends">friends</a></h3>
	
		<h3>카카오 로그인</h3>
		<div>
		 	<a href="https://kauth.kakao.com/oauth/authorize?client_id=d99aad64257f4c7b6af58515d60b1a79&redirect_uri=http://localhost:8000/kakao/oauth&response_type=code&scope=talk_message,friends">
				<img src="/images/kakao_login_large_wide.png" style="width:200px;">
			</a> 
			
<!-- 			<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=7980f70fd5da76a168557ced9cb2c60e&scope=talk_message&redirect_uri=http://localhost:8000/kakao/message"> talk </a>
 -->			<br>
			<a href="https://kauth.kakao.com/oauth/logout?client_id=7980f70fd5da76a168557ced9cb2c60e&logout_redirect_uri=http://localhost:8000/kakao/logout&response_type=code">logout</a>
		</div>
		
	<hr><hr>	

<script type="text/javascript">
function ckBtn(){
	var values = '';
	$("input[name=ckbox]:checked").each(function() {
	    console.log($(this).parent().closest("tr").text());
	});
	
	

}

</script>




	<table>
	<tbody id = "tbody">
	
	</tbody>
	</table>
<button onclick="ckBtn()">버튼</button>

	<script type="text/javascript">
		var mlist=[];
		var m = [];
		'<c:forEach var="m" items="${mlist }">'
		m.push("${m.id}")
		m.push("${m.pw}")
		m.push("${m.name}")
		mlist.push(m);
		m=[]
		'</c:forEach>'
		console.log(mlist)
		var str = '';
		var cnt = 0; 
		var newcnt = 0; 
		var id;
		for(var i = 0; i <mlist.length; i ++){
			str += '<tr>'
			for(var j = 0; j <mlist.length; j ++){
				if(mlist[i][0]==mlist[j][0]){
					cnt++;
					id = mlist[i][0]		
				}
			}
			if( mlist[i][0] == id )
			{
				if(cnt > 1)
				{
					if (newcnt == 0){
						str +='<td rowspan="' + cnt+ '" id="id" >'+mlist[i][0]+'</td>'
					}
				}else if(cnt==1){
					str +='<td  id="id">'+mlist[i][0]+'</td>'
				}
				str +='<td><input type="checkbox" name="ckbox"> '+mlist[i][1]+'</td>'
				
				if(cnt > 1)
				{
					if (newcnt == 0){
						str +='<td rowspan="' + cnt+ '" >'+mlist[i][2]+'</td>'
					}
					newcnt++;
					if (cnt == newcnt) newcnt=0;
				}else if(cnt==1){
					str +='<td>'+mlist[i][2]+'</td>'
				}
			}
			cnt=0;
			str+='</tr>'
		}
		console.log(str);
		$("#tbody").html(str)
		
		</script>
		
		
		<hr><hr>
		
		<c:forEach var="b" items="${blist }">
		
		<script>
		var list=[];
		list.push("${b.bdate }")
		</script>
		
		<br>
		</c:forEach>
		
		<script>
		
		var date = list[0];
		var dd= dateformat(date);
		console.log(dd)
		
		function dateformat(date){ 
			var t = date.split(' ');
			var ymd = t[0].split('-');
			return ymd[0]+"년 "+ymd[1]+"월 "+ymd[2]+"일";
		}

		</script>
		
		
		
	</body>
</html>