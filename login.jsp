<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<%if(request.getParameter("loginsuccess") != null){%><script type="text/javascript">alert("성공적으로 암호가 변경되었습니다.\n다시 로그인해주세요.");</script><%}%>
<%if(request.getParameter("joinsuccess") != null){%><script type="text/javascript">alert("회원가입이 되었습니다.\n지금부터 로그인이 가능합니다.");</script><%}%>
<%if(request.getParameter("loginerror") != null){%><script type="text/javascript">alert("다시 로그인해주세요");</script><%}%>
<%if(request.getParameter("errorlength") != null){%><script type="text/javascript">alert("아이디는 4글자 이상입니다.");</script><%}%>
<title>놀러가고싶조</title>
</head>
<style type="text/css">
.logintitle{
text-align: center;
font-weight: bold;
font-size: 50px;
}
.logindescription{
text-align: center;
margin-top: 10px;
font-size: 20px;
}
.logindetail{
margin-top: 30px;
}
.button{

margin-left: auto;
}
.findinfo{
margin-top: 30px;
}
.loginbox{
margin-top: 30px;
}
.jumbotron{
text-align: center;
}
.login4 {
	position: relative;
	top: 10px;
}
</style>

<body>
	<%@ include file="./menu.jsp" %>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px; position: relative; top: 200px;">
				<form method="post" action="./login">
				<div class="logintitle">
					LOGIN
				</div>	
				<div class="logindescription">
					저희 사이트에 오신 것을 환영합니다.
				</div>	
				<div class="loginbox">
					<div class="login1">
					<!-- 아이디<br>
					비밀번호 -->
					</div>
					<div class="login2">
						<div class="form-group">
							아이디<input type="text" class="form-control" placeholder="아이디를 입력하세요." name="id" maxlength="20" size="10">
						</div>
						<div class="form-group">
							비밀번호<input type="password" class="form-control" placeholder="비밀번호를 입력하세요." name="pw" maxlength="20" size="10">
						</div>
					</div>
					<div class="login3">
						<input type="submit" class="btn btn-primary form-control" value="로그인" >
					</div>
				</form>
					<div class="login4">
						<input type="button" onclick="location.href='./join'" class="btn btn-primary form-control" value="회원가입" >					
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>