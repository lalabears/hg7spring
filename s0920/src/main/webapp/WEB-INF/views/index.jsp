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


	
		
	</body>
</html>