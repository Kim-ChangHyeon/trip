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
<link rel="stylesheet" href="./css/location.css">
<script src=" https://code.jquery.com/jquery-3.4.1.js"></script>
<%if(request.getParameter("noadmin") != null){%><script type="text/javascript">alert("관리자권한이 없습니다.");</script><%}%>
<%if(request.getParameter("deletesucces") != null){%><script>alert("삭제가 완료되었습니다.");</script><%} %>
<%if(request.getParameter("deleteerror") != null){%><script>alert("관리자권한이 없습니다.");</script><%} %>
</head>
<body>
  <div id="container">

	<%@ include file="./menu.jsp"%>

    <div class="banner">
		<img alt="Banner" src="./img/location.jpg">
    </div>

    <div class="main">
      <div class="location"> 
      		<font style="font-weight: 900; font-size: 30px;">
      		<font style="text-decoration: overline;">LO</font>CATION /</font> 
      		<font style="font-weight: 700; color: #7a7a7a; font-size: 28px;">
      		<c:if test="${location eq 'seoul'}">서울</c:if>
      		<c:if test="${location eq 'gyeonggi'}">경기도</c:if>
      		<c:if test="${location eq 'gangwon'}">강원도</c:if>
      		<c:if test="${location eq 'chungcheong'}">충청도</c:if>
      		<c:if test="${location eq 'daejeon'}">대전</c:if>
      		<c:if test="${location eq 'gyeongsang'}">경상도</c:if>
      		<c:if test="${location eq 'daegu'}">대구</c:if>
      		<c:if test="${location eq 'busan'}">부산</c:if>
      		<c:if test="${location eq 'ulsan'}">울산</c:if>
      		<c:if test="${location eq 'jeolla'}">전라도</c:if>
      		<c:if test="${location eq 'gwangju'}">광주</c:if>
      		<c:if test="${location eq 'jeju'}">제주도</c:if>  		
      		</font>

      </div>
      
      <div class="listNum">
      	<c:if test="${on ne 1 }">
				<a href="location?location=${location }&limit=10&page=1&on=1&view=${view }" ><img src="./img/10off.jpg"></a>
			</c:if>
			<c:if test="${on eq 1 }">
				<a href="location?location=${location }&limit=10&page=1&on=1&view=${view }" ><img src="./img/10on.jpg"></a>
			</c:if>
			
			<c:if test="${on ne 2 }">
				<a href="location?location=${location }&limit=20&page=1&on=2&view=${view }" ><img src="./img/20off.jpg"></a>
			</c:if>
			<c:if test="${on eq 2 }">
				<a href="location?location=${location }&limit=20&page=1&on=2&view=${view }"><img src="./img/20on.jpg"></a>
			</c:if>
			
			<c:if test="${on ne 3 }">
				<a href="location?location=${location }&limit=1000&page=1&on=3&view=${view }"><img src="./img/alloff.jpg"></a>
			</c:if>
			<c:if test="${on eq 3 }">
				<a href="location?location=${location }&limit=1000&page=1&on=3&view=${view }"><img src="./img/allon.jpg"></a>
			</c:if>
      </div>
      
      <div class="listview">
      <c:if test="${view ne 'list'}">
			<a href="location?location=${location }&limit=${limit }&page=1&on=${on }&view=list" ><img src="./img/imgList-off.jpg"></a>
		</c:if>
      <c:if test="${view eq 'list' }">
			<a href="location?location=${location }&limit=${limit }&page=1&on=${on }&view=list"><img src="./img/imgList-on.jpg"></a>
    	</c:if>
    	<c:if test="${view ne 'board' }">
			<a href="location?location=${location }&limit=${limit }&page=1&on=${on }&view=board"><img src="./img/imgBoard-off.jpg"></a>
		</c:if>
      <c:if test="${view eq 'board' }">
			<a href="location?location=${location }&limit=${limit }&page=1&on=${on }&view=board"><img src="./img/imgBoard-on.jpg"></a>
    	</c:if>
      </div>
      
      <c:if test="${view eq 'list' }">
      <div class="list">
		<c:forEach items="${list }" var="row">
			<li>
				<div class="a1" onclick="popup(${row.location_no})"><img alt="사진" src="./upload/thumbnail/${row.location_file1 }" ></div>
				<div class="a2" onclick="popup(${row.location_no})">${row.location_title }</div>
				<div class="a3" onclick="popup(${row.location_no})">${row.location_location }</div>
			</li>
		</c:forEach>
		</div>
		</c:if>
		
		<c:if test="${view eq 'board' }">
      	<div class="board">
	      	<c:forEach items="${list }" var="row">
	      	<hr>
	      		<div class="board_file" onclick="popup(${row.location_no})"><img src="./upload/thumbnail/${row.location_file1 }" ></div>
	      		<div class="board_title" onclick="popup(${row.location_no})">${row.location_title }</div>
	      		<div class="board_location" onclick="popup(${row.location_no})">${row.location_location }</div>
	      		<div class="board_content" onclick="popup(${row.location_no})">${row.location_content }</div>
	      	</c:forEach>
	      	<hr>
      	</div>
      	</c:if>	
	</div>
		
		<div class="page">
			<fmt:parseNumber integerOnly="true" var="totalPage" value="${total / limit }"/>
			<c:if test="${(total % limit ge 1 )}">
				<c:set var="totalPage" value="${totalPage + 1 }"/>
			</c:if>
			<c:if test="${page ge 1 }">
				<a href="location?location=${location }&limit=${limit}&page=1&on=${on }&view=${view }"><img src="./img/first.jpg"></a>
			</c:if>
			
			<c:if test="${page ge 1 }">
				<c:if test="${page eq 1 }">
					<img src="./img/left.jpg">
				</c:if>
				<c:if test="${page ne 1 }">
					<a href="location?location=${location }&limit=${limit}&page=${page-1 }&on=${on }&view=${view }"><img src="./img/left.jpg"></a>
				</c:if>
			</c:if>
			<c:forEach begin="1" end="${totalPage }" var="i">
			<span>
				<a href="location?location=${location }&page=${i }&limit=${limit }&on=${on }&view=${view }" id="${page eq i ? 'on' : null }">${i }</a>
				<c:if test="${totalPage gt i}">ㅣ</c:if>
			</span>
			</c:forEach>
			
			<c:if test="${page le totalPage }">
				<c:if test="${page eq totalPage }">
				<img src="./img/right.jpg">
				</c:if>
				<c:if test="${page ne totalPage }">
				<a href="location?location=${location }&limit=${limit}&page=${page+1 }&on=${on }&view=${view }"><img src="./img/right.jpg"></a>
				</c:if>
			</c:if>
			
			<c:if test="${page le totalPage }">
				<a href="location?location=${location }&limit=${limit}&page=${totalPage }&on=${on }&view=${view }"><img src="./img/last.jpg"></a>
			</c:if>
		</div>
	<c:if test="${grade eq 1 }">	
    	<div class="button"><a href="./locationwrite?location=${location }" id="write">글작성</a></div>
    </c:if>
    <div class="copyright" style="text-align: center; font-size: 20px">
    	Copyright(C) 2021.놀러가고싶조.All rights reserved.
	</div>
    </div>
  	<script>
  		function popup(num) {
  			location.href="./popup?location=${location }&limit=${limit}&page=${page }&on=${on }&view=${view }&pop="+num;
  		}
  	</script>
</body>
</html>
