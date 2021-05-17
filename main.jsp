<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>놀러가고싶조</title>
<link rel="stylesheet" href="./css/main.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Yeon+Sung&display=swap" rel="stylesheet">
<meta charset="UTF-8"><link rel="apple-touch-icon" sizes="57x57" href="/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60" href="/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72" href="/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76" href="/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114" href="/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120" href="/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144" href="/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152" href="/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180" href="/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"  href="/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
<link rel="manifest" href="/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">
<script src=" https://code.jquery.com/jquery-3.4.1.js"></script>
<%if(request.getParameter("delsuccess") != null){%>	<script type="text/javascript">alert("성공적으로 삭제되었습니다.");</script><%}%>
<%if(request.getParameter("delunsuccess") != null){%>	<script type="text/javascript">alert("계정 삭제에 실패했습니다.");</script><%}%>
<style>
	* {margin:0;padding:0;}
	.section input[id*="slide"] {display:none;}
	.section .slidewrap {margin:0 auto;}
	.section .slidelist {white-space:nowrap;font-size:0;overflow:hidden;position:relative;}
	.section .slidelist > li {display:inline-block;vertical-align:middle;width:100%;transition:all .5s;}
	.section .slidelist > li > a {display:block;position:relative;}
	.section .slidelist > li > a img {width:100%; height: 600px;}
	.section .slidelist label {position:absolute;z-index:10;top:50%;transform:translateY(-50%);padding:50px;cursor:pointer;}
	.section .slidelist .textbox {position:absolute;z-index:1;top:100%;left:0;transform:translate(0,-100%);line-height:1.6;text-align:center;width: 450px; height: 200px; background-color: rgba(72,72,72,0.5);}
	.section .slidelist .textbox h3 {font-size:36px;color:#fff;transform:translateY(30px);transition:all .5s;}
	.section .slidelist .textbox p {font-size:16px;color:#fff;opacity:0;transform:translateY(30px);transition:all .5s;}
	
	.section input[id="slide01"]:checked ~ .slidewrap .slidelist > li {transform:translateX(0%);}
	.section input[id="slide02"]:checked ~ .slidewrap .slidelist > li {transform:translateX(-100%);}
	.section input[id="slide03"]:checked ~ .slidewrap .slidelist > li {transform:translateX(-200%);}
	
	.section input[id="slide01"]:checked ~ .slidewrap li:nth-child(1) .textbox h3 {opacity:1;transform:translateY(0);transition-delay:.2s; position: relative; top:50%; left: 50%; transform: translate(-50%,-100%);}
	.section input[id="slide01"]:checked ~ .slidewrap li:nth-child(1) .textbox p {opacity:1;transform:translateY(0);transition-delay:.4s; position: relative; top:50%; left: 50%; transform: translate(-50%,-100%);}
	.section input[id="slide02"]:checked ~ .slidewrap li:nth-child(2) .textbox h3 {opacity:1;transform:translateY(0);transition-delay:.2s; position: relative; top:50%; left: 50% ;transform: translate(-50%,-100%);}
	.section input[id="slide02"]:checked ~ .slidewrap li:nth-child(2) .textbox p {opacity:1;transform:translateY(0);transition-delay:.4s; position: relative; top:50%; left: 50%; transform: translate(-50%,-100%);}
	.section input[id="slide03"]:checked ~ .slidewrap li:nth-child(3) .textbox h3 {opacity:1;transform:translateY(0);transition-delay:.2s; position: relative; top:50%; left: 50%; transform: translate(-50%,-100%);}
	.section input[id="slide03"]:checked ~ .slidewrap li:nth-child(3) .textbox p {opacity:1;transform:translateY(0);transition-delay:.4s; position: relative; top:50%; left: 50%; transform: translate(-50%,-100%);}
	
	.slide-control > div {display:none;}
	.section .left {left:30px;background:url('./img/left.png') center center / 100% no-repeat;}
	.section .right {right:30px;background:url('./img/right.png') center center / 100% no-repeat;}
	.section input[id="slide01"]:checked ~ .slidewrap .slide-control > div:nth-child(1) {display:block;}
	.section input[id="slide02"]:checked ~ .slidewrap .slide-control > div:nth-child(2) {display:block;}
	.section input[id="slide03"]:checked ~ .slidewrap .slide-control > div:nth-child(3) {display:block;}

	.slide-pagelist {text-align:center;padding:20px;position: absolute;top: 510px;left: 50%;transform: translate(-50%,0); width: 100%;}
	.slide-pagelist > li {display:inline-block;vertical-align:middle;}
	.slide-pagelist > li > label {display:block;padding:8px 30px;border-radius:30px;background:#ccc;margin:20px 10px;cursor:pointer;}
	.section input[id="slide01"]:checked ~ .slidewrap .slide-pagelist > li:nth-child(1) > label {background:#999;}
	.section input[id="slide02"]:checked ~ .slidewrap .slide-pagelist > li:nth-child(2) > label {background:#999;}
	.section input[id="slide03"]:checked ~ .slidewrap .slide-pagelist > li:nth-child(3) > label {background:#999;}
	</style>
</head>
<body>
 	<div id="container">

		<%@ include file="./menu.jsp"%>
	
		<div class="main_banner">
			<div class="section">
			<input type="radio" name="slide" id="slide01" checked>
			<input type="radio" name="slide" id="slide02">
			<input type="radio" name="slide" id="slide03">
			<div class="slidewrap">
				<ul class="slidelist">
					<!-- 슬라이드 영역 -->
					<li class="slideitem">
						<a>
							<img src="./img/main_banner11.jpg">
						</a>
					</li>
					<li class="slideitem">
						<a>

							<img src="./img/main_banner22.jpg">
						</a>
					</li>
					<li class="slideitem">
						<a>
							<img src="./img/main_banner33.jpg">
						</a>
					</li>
					<!-- 좌,우 슬라이드 버튼 -->
					<div class="slide-control">
						<div>
							<label for="slide03" class="left"></label>
							<label for="slide02" class="right"></label>
						</div>
						<div>
							<label for="slide01" class="left"></label>
							<label for="slide03" class="right"></label>
							
						</div>
						<div>
							<label for="slide02" class="left"></label>
							<label for="slide01" class="right"></label>
						</div>
					</div>
		
					</ul>
					<!-- 페이징 -->
					<ul class="slide-pagelist">
						<li><label for="slide01"></label></li>
						<li><label for="slide02"></label></li>
						<li><label for="slide03"></label></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
  		
  		<div class="best_tour">
	  		<div id="title">
	  			<img src="./img/main_review.png">
	  			<font style="font-weight: 900; font-size: 40px;font-family: 'Yeon Sung', cursive; ">BEST 여행지</font>
	  			<img src="./img/main_review.png">
	  		</div>
  			<hr>
  			<div id="tour_img">
  			<c:forEach items="${bestList }" var="row1">
			<li><div class="tour">
			 	<img alt="thumbnail" src="./upload/thumbnail/${row1.rfile1 }" onclick="location.href='./tripdetail?bno=${row1.rno}'">
					<a href="tripdetail?bno=${row1.rno }" class="more">${row1.rtitle }</a>
			</div></li>
			</c:forEach>	
  			</div>
  			<hr>
  		</div>
  		
  		<div class="best_theme">
  			<div id="title">
	  			<img src="./img/main_review.png">
	  			<font style="font-weight: 900; font-size: 40px; font-family: 'Yeon Sung', cursive;">BEST 추천 테마</font>
	  			<img src="./img/main_review.png">
	  		</div>
  			<hr>
  			<div id="tour_img">
		  		<c:forEach items="${tBestList }" var="row2">
				<li><div class="tour">
			 		<img alt="thumbnail" src="./upload/thumbnail/${row2.theme_file1 }" onclick="location.href='themepopup?theme=food&pop=${row2.theme_no }'">
						<a href="themepopup?theme=food&pop=${row2.theme_no }" class="more">${row2.theme_title }</a>
				</div></li>
				</c:forEach>
  			</div>
  			<hr>
  		</div>
  	<div class="copyright" style="text-align: center; font-size: 20px">
    	Copyright(C) 2021.놀러가고싶조.All rights reserved.
	</div>
</body>
</html>
