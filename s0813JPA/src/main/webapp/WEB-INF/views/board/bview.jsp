<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>bview.jsp</title>
		<style type="text/css">
		*{margin:0; padding:0;}
		div{width:800px; margin: 30px auto; }
		h1{margin-bottom: 30px;}
		table,th,td{border: 1px solid black; border-collapse:collapse; 
				font-size: 16px;}
		th { width: 300px; height: 40px;}
		td { width: 500px; height: 40px;}
		button{width:200px; height: 60px; margin-top: 30px;}
		ul li {list-style-type: none;}
		/* reply */
.replyWrite{width:100%; margin:50px 0 30px 0; overflow:hidden;}
.replyWrite ul li{float:left;}
.replyWrite ul li.in{position:relative; width:82%; padding:25px 0 0 0;}
.replyWrite ul li.in p.txt{position:absolute; left:0; top:4px; font-size:13px; font-weight:600; color:#666;}
.replyWrite ul li.in p.password{position:absolute; right:-2px; top:0; line-height:20px;} 
.replyWrite ul li.btn{margin:1px 0 0 10px;}
.replyWrite p.ntic{clear:both; padding:5px 0;}
.replyType{width:100%; height:58px; border:1px #d9d9d9 solid; padding:0; font-family:'Nanum Gothic';}
.replyBtn{display:block; width:110px; height:85px; line-height:85px; color:#fff; font-size:14px; font-weight:600; background:#5a524c; text-align:center;}
.replyBtn:hover{color:#fff; text-decoration:none;}
.replyBox{width:100%; border-top:1px #d9d9d9 solid; overflow:hidden;}
.replyBox ul{position:relative; border-bottom:1px #d9d9d9 solid; margin:15px 0 0 0; padding:0 20px 15px 20px;}
.replyBox ul li.name{font-size:14px; font-weight:600; color:#555; padding:0 0 10px 0;}
.replyBox ul li.name span{font-size:12px; color:#888; font-weight:normal;}
.replyBox ul li.txt{width:100%; color:#888; line-height:1.5; font-size:13px; word-break:keep-all;}
.replyBox ul li.btn{text-align:right; height:20px; padding:3px 0 0 0;}
.replyBox ul li.btn .rebtn{display:inline-block; width:43px; height:23px; line-height:22px; color:#777; font-weight:600; text-align:center; background:url('../images/btn/btn_daybtn.gif') left top no-repeat;}
.replyBox ul li.btn .rebtn:hover{color:#777;}
.replynum{width:82px; height:20px; line-height:20px; border:1px #d9d9d9 solid; padding:0 0 0 5px;}
.finReply{margin:50px 0 0 0;}
		
		</style>
	</head>
	<body>
		<div>
			<h1>게시글보기</h1>
			<table>
				<tr>
					<th>번호</th>
					<td>${board.bno }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${board.btitle }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${board.bcontent }</td>
				</tr>
				<tr>
					<th>날짜</th>
					<td>${board.bdate }</td>
				</tr>
				<tr>
		       		<th>파일첨부</th>
		       		<td>${board.bfile }</td>
		     	</tr>
		     	
			</table>
			
			<!-- 댓글-->
					<div class="replyWrite">
						<ul>
							<li class="in">
								<p class="txt">총 <span class="orange">3</span> 개의 댓글이 달려있습니다.</p>
								<p class="password">비밀번호&nbsp;&nbsp;<input type="password" class="replynum" /></p>
								<textarea class="replyType"></textarea>
							</li>
							<li class="btn"><a href="#" class="replyBtn">등록</a></li>
						</ul>
						<p class="ntic">※ 비밀번호를 입력하시면 댓글이 비밀글로 등록 됩니다.</p>
					</div>
					<div class="replyBox">
						<ul>
							<li class="name">jjabcde <span>[2014-03-04&nbsp;&nbsp;15:01:59]</span></li>
							<li class="txt">대박!!! 이거 저한테 완전 필요한 이벤트였어요!!</li>
							<li class="btn">
								<a href="#" class="rebtn">수정</a>
								<a href="#" class="rebtn">삭제</a>
							</li>
						</ul>
					</div>
					<!-- //댓글 -->
			
			<a href="/board/bmodi?bno=${board.bno }"><button type="button">게시글수정</button></a>
			<a href="/board/bdelete?bno=${board.bno }"><button type="button">게시글삭제</button></a>
			<a href="/board/blist"><button type="button">게시글리스트</button></a>
		
		</div>
		
		
		
		
		
	
	</body>
</html>