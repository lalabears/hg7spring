<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>checkbox.jsp</title>
		<style>
		table,td{border: 1px solid black; border-collapse:collapse;}
		input[type="checkbox"]:focus {
  border-color: #ffffcc;
}
		</style>
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
		$(function(){
			$("#checkAll").click(function(){
				// alert('체크박스를 클릭하셨습니다');
				if($("#checkAll").is(":checked")){
					$("input[name=agree]").prop("checked", true);
				}else{
					$("input[name=agree]").prop("checked", false);
				}
			}); // $("#checkAll").click 
			
			$("input[name=agree]").click(function(){
				var total = $("input[name=agree]").length; 
				var checked = $("input[name=agree]:checked").length; 
				//alert("전체 동의 체크박스 수 : " + total);
				//alert("체크된 동의 체크박스 수 : " + checked);
				if(total != checked ) {
					$("#checkAll").prop("checked", false);
				}else{
					$("#checkAll").prop("checked", true);
				}
			}); 
		}); // jquery
		function sBtn(){
			if($("input[name=agree]:checked").length != 3){
				
				if(!$("#a1").is(":checked")){
					alert("option1")
					$("#a1").css({"color": "blue", "font-size": "20px"});
				}
				else if(!$("#a2").is(":checked")){
					alert("option2")
					$("#a2").focus();
				}
				else if(!$("#a3").is(":checked")){
					alert("option3")
					$("#a3").focus();
				}
				
				/*if(confirm("전체동의를 하셔야 가입이 가능합니다\n전체동의 하시겠습니까?")){
					$("input[name=agree]").prop("checked", true);
				}*/
			}
		} // sBtn
		</script>
	</head>
	<body>
		<table>
			<tr>
				<td colspan="3"><input type="checkbox" id="checkAll"> 전체선택 </td>
			</tr>
			<tr>
				<td> <input type="checkbox" id="a1" name="agree" /> option1 </td>
				<td> <input type="checkbox" id="a2" name="agree" /> option2 </td>
				<td> <input type="checkbox" id="a3" name="agree" /> option3 </td>
			</tr>
		</table>
		<br>
		<button type="button" onclick="sBtn()" >가입</button>
	
	</body>
</html>