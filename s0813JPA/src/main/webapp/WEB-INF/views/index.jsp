<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>index jsp</title>
	</head>
	<body>
		<ul>
	<c:if test="${sessionId == null }">
			<li><a href="/member/login">로그인</a></li>
			<li><a href="/member/join">회원가입</a></li>
	</c:if>	
	<c:if test="${sessionId != null }">
			<li>${sessionName }님 환영합니다. </li>
			<li><a href="/member/updateMem">마이페이지</a></li>
			<li><a href="/member/mlist">회원리스트</a></li>
			<li><a href="/member/logout">로그아웃</a></li>
	</c:if>
		</ul>
		
	</body>
</html>