<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Yeon+Sung&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>놀러가고싶조</title>
<%HttpSession ses = request.getSession(); %>
</head>
<body>
<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a href="./main"><img id="logo" alt="logo" src="./img/logo1.png"></a>
			<a id="font" class="navbar-brand" href="./main">놀러가고 싶조</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li id="item"><a id="font" href="./location?location=gyeonggi">지역</a>
					<ul id="submenu1">				
						<li><a href="./location?location=gyeonggi">경기</a></li>
						<li><a href="./location?location=seoul">서울</a></li>
						<li><a href="./location?location=gangwon">강원</a></li>
						<li><a href="./location?location=chungcheong">충청</a></li>
						<li><a href="./location?location=daejeon">대전</a></li>
						<li><a href="./location?location=gyeongsang">경상</a></li>
						<li><a href="./location?location=daegu">대구</a></li>
						<li><a href="./location?location=busan">부산</a></li>
						<li><a href="./location?location=ulsan">울산</a></li>
						<li><a href="./location?location=jeolla">전라</a></li>
						<li><a href="./location?location=gwangju">광주</a></li>
						<li><a href="./location?location=jeju">제주</a></li>
					</ul>
				</li>
				<li id="item"><a id="font" href="./theme?theme=food">테마</a>
					<ul id="submenu2">
						<li><a href="./theme?theme=food">맛집</a></li>
						<li><a href="./theme?theme=tour">관광</a></li>
						<li><a href="./theme?theme=play">체험</a></li>
					</ul>
				</li>	
				<li><a id="font" href="./tripreview">여행후기</a></li>
				<li><a id="font" href="./notice">공지사항</a></li>
				<c:if test="${grade eq 1 && member_no eq 10}">
				<li ><a id="font" style="color: red;" href="./admin">회원관리</a></li>
				</c:if>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${loginCheck ne 1}">
				<li class="dropdown">
					<a id="font" href="#" class="dropdwon-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">접속하기<span class="caret"></span></a>
					</c:if>
					
					<c:if test="${loginCheck eq 1}">
					<li class="dropdown">
					<a id="font" href="#" class="dropdwon-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false" style="min-width: 148px;"><%=ses.getAttribute("member_name") %>님<span class="caret"></span></a>
					</c:if>
					
					<c:if test="${loginCheck ne 1}">
					<ul class="dropdown-menu" style="min-width:123px ">
						<li class="active"><a id="font" href="./login">로그인</a></li>
						<li><a id="font" href="./join">회원가입</a></li>
					</c:if>
					<c:if test="${loginCheck eq 1}">
					<ul class="dropdown-menu">
						<li class="active"><a id="font" href="./myinfo">내 정보</a></li>
						<li><a id="font" href="./logoutAction.jsp">로그아웃</a></li>
					</c:if>
					</ul>
			</ul>
		</div>	
	</nav>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>