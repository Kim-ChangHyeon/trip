<%@page import="com.poseidon.dao.TripDAO"%>
<%@page import="com.poseidon.dao.LoginDAO"%>
<%@page import="com.poseidon.dto.LoginDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>놀러가고싶조</title>
<link rel="stylesheet" href="./css/location.css">
<link rel="stylesheet" href="./css/tripreview.css">
<style type="text/css">
.banner {margin-top: 0;}
hr {margin-top: 0; margin-bottom: 0}
</style>
<%TripDAO dao = new TripDAO();%>
</head>
<body>
	<div id="container">
	<%@ include file="./menu.jsp" %>
	<div class="banner">
		<img alt="Banner" src="./img/review.jpg">
    </div>
    <div class="main">
      <div class="location"> 
      		<font style="font-weight: 900; font-size: 30px;">
      		<font style="text-decoration: overline;">RE</font>VIEW</font> 
    	</div>
    	<br>
    <div class="best_title" id="c">
    <img src="./img/main_review.png" style="position: relative; top: -6px; margin-right: 10px;">
		<font style="font-weight: 900; font-size: 30px;font-family: 'Yeon Sung', cursive; ">BEST 여행후기</font>
		<img src="./img/main_review.png" style="position: relative; top: -6px; margin-left: 10px;">
	</div>
	<hr>
	<div id="bestreview">
		<div id="local" class="title">
			<c:forEach items="${bestList }" var="row1">
			<div id="first" class="title">
			 	<img alt="thumbnail" src="./upload/thumbnail/${row1.rfile1 }" onclick="location.href='./tripdetail?bno=${row1.rno}'">
					<a href="tripdetail?bno=${row1.rno }" class="more">${row1.rtitle }</a>
			</div>
			</c:forEach>	
		</div>
	</div>
			<div id="content">
				<table>
		<tr>
			<th id="c">번호</th>
			<th id="c">지역</th>
			<th id="c">제목</th>
			<th id="c">작성자</th>
			<th id="c">날짜</th> 
			<th id="c">조회수</th>
		</tr>
		<c:forEach items="${list }" var="row">
		<tr onclick="location.href='./tripdetail?bno=${row.rno}'"> 
			<td>${row.rno } </td>
		<c:choose>
			<c:when test="${row.rlocation eq 1 }"><td>서울 </td> </c:when>
			<c:when test="${row.rlocation eq 2 }"><td>인천 </td></c:when>
			<c:when test="${row.rlocation eq 3 }"><td>경기 </td></c:when>
			<c:when test="${row.rlocation eq 4 }"><td>강원 </td></c:when>
			<c:when test="${row.rlocation eq 5 }"><td>경상 </td></c:when>
			<c:when test="${row.rlocation eq 6 }"><td>충청 </td></c:when>
			<c:when test="${row.rlocation eq 7 }"><td>전라 </td></c:when>
			<c:when test="${row.rlocation eq 8 }"><td>제주 </td></c:when>
		</c:choose>
		
		<td> ${row.rtitle }
		<c:forEach items="${commentcount }" var="com"><c:if test="${com.rno eq row.rno }"><font style="color: red;">(${com.commentcount })<font></font></c:if></c:forEach></td>
			<td>${row.member_name } </td>
			<td>${row.rdate } </td>
			<td>${row.rviews } </td>
		</tr>
		</c:forEach>
				</table>
				
			<c:if test="${grade eq 1 || grade eq 2 }">
					<button id="wbtn" onclick="location.href='./tripwrite'">글쓰기</button>
			</c:if>
			</div>
		
    </div>
		<div class="page">
			<fmt:parseNumber integerOnly="true" var="totalPage" value="${count / 10 }"/>
			<c:if test="${(count % 10 ge 1 )}">
				<c:set var="totalPage" value="${totalPage + 1 }"/>
			</c:if>
			<c:if test="${page ge 1 }">
				<a href="tripreview?page=1&limit=10"><img src="./img/first.jpg"></a>
			</c:if>
			
			<c:if test="${page ge 1 }">
				<c:if test="${page eq 1 }">
					<img src="./img/left.jpg">
				</c:if>
				<c:if test="${page ne 1 }">
					<a href="tripreview?page=${page - 1 }&limit=10"><img src="./img/left.jpg"></a>
				</c:if>
			</c:if>
			<c:forEach begin="1" end="${totalPage }" var="i">
			<span>
				<a href="tripreview?page=${i }&limit=10" id="${page eq i ? 'on' : null }">${i }</a>
				<c:if test="${totalPage gt i}">ㅣ</c:if>
			</span>
			</c:forEach>
			
			<c:if test="${page le totalPage }">
				<c:if test="${page eq totalPage }">
				<img src="./img/right.jpg">
				</c:if>
				<c:if test="${page ne totalPage }">
				<a href="tripreview?page=${page+1 }&limit=10"><img src="./img/right.jpg"></a>
				</c:if>
			</c:if>
			
			<c:if test="${page le totalPage }">
				<a href="tripreview?page=${totalPage }&limit=10"><img src="./img/last.jpg"></a>
			</c:if>
		</div> 
    <div class="copyright" style="text-align: center; font-size: 20px">
    	Copyright(C) 2021.놀러가고싶조.All rights reserved.
	</div>
	</div>
</body>
</html>