<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.poseidon.dao.LoginDAO"%>
<%@page import="com.poseidon.dto.LoginDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html oncontextmenu='return false' onselectstart='return false' ondragstart='return false'>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>놀러가고싶조</title>
</head>
<style type="text/css"></style>
<body>
<%
if(request.getParameter("error") != null){
	%>
	<script type="text/javascript">
	alert("암호가 다릅니다.\n다시 시도해주세요.");
	</script>
	<%
}
%>
<%
String member_id = null;
if(session.getAttribute("member_id") != null) {
	member_id = (String) session.getAttribute("member_id");
	
}else if (session.getAttribute("member_id") == null) {
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('로그인을 하세요.')");
	script.println("location.href='login.jsp'");
	script.println("</script>");
}

%>
<script type="text/javascript">
function pwmd(){
	//var pw="${dto.member_pw}";
	var pw1 = document.getElementById("pw");
	var npw = document.getElementById("npw");
	var npw2 = document.getElementById("npw2");
	
	if(pw1.value == ""){
		alert("비밀번호가 비어있습니다.")
		pw1.focus();
		return false;
	}

	/*if(pw1.value!=pw){
		alert("기존 비밀번호와 일치하지 않습니다.");
		pw1.focus();
		return false;
	} */
	if(npw.value==""){
		alert("새 비밀번호를 입력해주세요.");
		npw.focus();
		return false;
	}
	if(npw2.value==""){
		alert("새 비밀번호 확인을 입력해주세요.");
		npw2.focus();
		return false;
	}
	if(npw.value!=npw2.value){
		alert("새 비밀번호와 확인이 서로 다릅니다.");
		npw2.focus();
		return false;
	}
}
function joinoutcheck(){
	//location.href='./joinout'
	var pw="${dto.member_pw}";
	var pw1 = document.getElementById("pw");
	if(pw1.value == ""){
		alert("비밀번호가 비어있습니다.");
		pw1.focus();
		return false;
		}
		if(pw1.value!=pw){
		alert("기존 비밀번호와 일치하지 않습니다.");
		pw1.focus();
		return false;
		}else{
			location.href="./joinout";
		}
	}

</script>

	<%@ include file="./menu.jsp" %>
	<div class="jumbotron" style="position: relative;top: 70px;">
	<div class="container">
			<h1 class="text-muted text-center mt-3 mb-3">내 정보</h1>
			<form action="./pwmd" method="post" >
			
				<table class="table">
					<colgroup>
						<col width="25%">
						<col width="75%">
					</colgroup>
					<tbody>
					
						<tr>
							<th class="text-right">아이디</th>
							<td>
							<div class="col-xs-3">
							
							${dto.member_id }
								
							</div>	
							
							</td>
						</tr>
						<tr>
							<th class="text-right">비밀번호 확인</th>
							<td>
							<div class="col-xs-3">
								<input type="password" class="form-control" id="pw" name="pw" maxlength="20" placeholder="비밀번호를 입력하세요." value="">
							</div>	
							</td>
						</tr>
						<tr>
							<th class="text-right">새 비밀번호</th>
							<td>
							<div class="col-xs-3">
								<input type="password" class="form-control" id="npw" name="npw" maxlength="20" placeholder="비밀번호를 입력하세요." value="">
							</div>	
							</td>
						</tr>
						<tr>
							<th class="text-right">새 비밀번호 확인</th>
							<td>
							<div class="col-xs-3">
								<input type="password" class="form-control" id="npw2" name="npw2" maxlength="20" placeholder="비밀번호를 입력하세요." value="">
							</div>	
							</td>
						</tr>
						<tr>
							<th class="text-right">이름</th>
							<td>
							<div class="col-xs-3">
								${dto.member_name }
							</div>
							</td>
						</tr>
						<tr>
							<th class="text-right">생년월일</th>
							<td>
							<div class="col-xs-3">
								${dto.member_birth }
							</div>
							</td>
						</tr>
						
						<tr>
							<th class="text-right">E-mail</th>
							<td class="form-inline">
							
								${dto.member_email }
							
							</td>
						</tr>
						<tr>
							<th class="text-right">핸드폰</th>
							<td class="form-inline">
							
							${dto.member_tel }
							
							</td>
						</tr>
						<tr>
							<th class="text-right">주소</th>
							<td class="form-inline">
							${dto.member_addr }
							</td>
						</tr>
					
					</tbody>
				</table>
			<div class="text-center">
			<button type="submit" class="btn btn-primary" onclick="return pwmd()">비밀번호 변경하기</button>
			<button type="button" class="btn btn-primary" onclick="return joinoutcheck()">탈퇴하기</button>
			</div>
			</form>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>