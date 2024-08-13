<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<title>회원가입</title>
		<style>
			*{margin: 0; padding: 0; }
			div{width: 400px; margin: 30px auto; text-align:center;}
			h1{margin-bottom: 30px;}
			table, th, td{border: 1px solid black; border-collapse: collapse;
							font-size: 16px;}
			th{width: 200px; height: 40px;}
			td{width: 400px; height: 40px;}
			button{width: 150px; height: 40px; margin-top: 30px;}
		</style>
		<script>
		$(function(){
			$("#fbtn").click(function(){
				alert("회원가입");
				m_frm.submit();
			});//fbtn
		});//jquery
		</script>
	</head>
	<body>
		<div>
			<h1>회원가입</h1>
			<form action="/member/updateMem" name="m_frm" method="post">
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" id="id" name="id" value="${mem.id }" readonly></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" id="pw" name="pw" value="${mem.pw }" ></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" id="name" name="name" value="${mem.name }" readonly ></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="text" id="phone" name="phone" value="${mem.phone }"></td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
						<input type="radio" id="Male" name="gender" value="Male"
						  <c:if test="${mem.gender == 'Male' }"> checked </c:if> 
						/>
						<label for="Male">남성</label>
						<input type="radio" id="Female" name="gender" value="Female"
						 <c:if test="${mem.gender == 'Female' }"> checked </c:if>
						/>
						<label for="Female">여성</label>
					</td>
				</tr>
				<tr>
					<th>취미</th>
					<td>
						<input type="checkbox" name="hobby" id="game" value="game"
						<c:if test="${mem.hobbys.contains('game') }"> checked </c:if>
						>
						<label for="game">게임</label>
						<input type="checkbox" name="hobby" id="golf" value="golf"
						<c:if test="${mem.hobbys.contains('golf') }"> checked </c:if> >
						<label for="golf">골프</label>
						<input type="checkbox" name="hobby" id="run" value="run"
						<c:if test="${mem.hobbys.contains('run') }"> checked </c:if> >
						<label for="run">조깅</label>
						<input type="checkbox" name="hobby" id="cook" value="cook"
						<c:if test="${mem.hobbys.contains('cook') }"> checked </c:if> >
						<label for="cook">요리</label>
						<input type="checkbox" name="hobby" id="book" value="book"
						<c:if test="${mem.hobbys.contains('book') }"> checked </c:if>  >
						<label for="book">독서</label>
					</td>
				</tr>
			</table>
			<button type="button" id="fbtn">저장</button>			
			<button type="button" onclick="javascript:history.back()">취소</button>			
			</form>
		
		</div>
	
	</body>
</html>