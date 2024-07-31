<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>inputCheck.jsp</title>
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			function ckBtn(){
				// if($("#id").val().length < 5 ) alert("5자리 이상입력");
				// 이름은 한글로만 입력을 받는다. 
				let nameCheck = /^[가-힣]+$/;   // 한글검사
				let numCheck = /^[0-9]+$/;     // 숫자검사
				let alphaCheck = /^[a-zA-Z]+$/;// 영문검사 
				let etcCheck = /^[!@#$%^&*,.?_-]+$/; // 포함된 특수문자 검사
				
				
				// id 검사 숫자, 영문자만가능 영문자로시작. 특수문자는 _  만가능
				let idCheck01 = /^[a-zA-Z0-9_]{5,10}$/;
				// 첫글자는 영문
				let idCheck02 = /^[a-zA-Z]{1}[a-zA-Z0-9_]{3,6}$/;
				// 영문자, 숫자, 특수문자는 @-_.
				let emailCheck =  /^[a-zA-Z0-9@._-]+$/;
				// 비밀번호검사. 
				// 하나이상의 영문자, 하나이상의 숫자, 하나이상의 특수문자 최소 8자리
				let pwCheck = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{5,10}$/;
				
				if( !pwCheck.test( $("#pw").val() ) ) {
					alert("");
					$("#pw").focus();
				}
				if( !idCheck02.test( $("#id").val() ) ) {
					alert("아이디는 영문,숫자 _ 만 허용합니다. (4-7자리까지가능)");
					$("#id").focus();
				}
				if( !nameCheck.test( $("#name").val() ) ) {
					alert("한글만 입력하셔야 합니다");
					$("#name").focus();
				}
				if( !emailCheck.test( $("#email").val() ) ) {
					alert("영문자, 숫자, 특수문자는 @-_. 만 허용함");
					$("#email").focus();
				}
				
			}// ckBtn
		
		</script>
		
		<style>
		.blue{color:blue;}
		.red{color:red;}
		</style>
	</head>
	<body>
		<h1>정규식</h1>
		아이디  : <input type="text" id="id" name="id"><br>
		이름    : <input type="text" id="name" name="name"><br>
		비밀번호 : <input type="text" id="pw" name="pw"><br>
		이메일  : <input type="text" id="email" name="email"><br>
		<button type="button" onclick="ckBtn()"> 가입</button>
		<hr><hr>
<script type="text/javascript">
function pwKey(){
	console.log($("#pw2").val());
	let pw1 = $("#pw1").val(); 
	if(pw1 == $("#pw2").val() ){
		$("#pwCk").html('<span class="blue">비밀번호가 일치합니다</span>');
	}else{
		$("#pwCk").html('<span class="red">비밀번호가 일치하지 않습니다</span>');
	}
}
</script>		
		<h1>비밀번호 비교하기</h1>
		비밀번호 입력 <input type="text" id="pw1" name="pw1"><br>
		비밀번호 확인 <input type="text" id="pw2" name="pw2" onkeyup="pwKey()"><br>
		<p id="pwCk"></p>
		<br><hr><br>
		<h1> select option - 01월부터 12월까지 </h1>
		<select>
			<script>
				for(var i = 1 ; i <= 12 ; i ++ ){
					if( i < 10 ){
						document.write("<option value='"+i+"'>0"+ i + "월</option>")
					}else{
						document.write("<option value='"+i+"'>"+ i + "월</option>")
					}
				}
			</script>
		</select>
		<br><hr><br>
<script type="text/javascript">
function hbCk(){
	console.log($("#hobbyList").val());
	if( $("#hobbyList").val() != "txt"){
		$("#hobby").val( $("#hobbyList").val() );
		$("#hobby").attr("disabled",true);
	} else {
		$("#hobby").val( "" );
		$("#hobby").attr("disabled",false);
	}
}
</script>
		<h1> select 선택된 값을 input 텍스트에 넣기 </h1>
		<input type="text" id="hobby" name="hobby" placeholder="직접입력">
		<select id = "hobbyList" onchange="hbCk()" >
			<option value="txt"></option>
			<option value="book">book</option>
			<option value="run">run</option>
			<option value="cook">cook</option>
			<option value="golf">golf</option>
		</select>		
		<br><hr><br>
<script type="text/javascript">
function idCkBtn(){
	//alert($("#userId").val());
	$.ajax({
		url : "/member/idck",
		type : "post",
		data : {"id": $("#userId").val() },
		success: function(data){
			alert("성공");
			console.log(data)
		}, 
		error: function(){
			alert("실패")
		}
		
	});// ajax
} // idCkBtn

</script>
		<h1> 아이디 중복 검사 ajax </h1>
		
		<input type="text" id="userId" name="userId" > 
		<button type="button" onclick="idCkBtn()">아이디중복확인</button>
	
	
	
	
	
	<br><br><br>
		
	</body>
</html>