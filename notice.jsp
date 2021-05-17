<%@ page import="com.poseidon.dao.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>놀러가고싶조</title>
<link href="./css/location.css" rel="stylesheet">
<%if(request.getParameter("deleteerror") != null){%><script type="text/javascript">alert("삭제중 오류가 발생했습니다.");</script><%}%>
<%if(request.getParameter("noadmin") != null){%><script type="text/javascript">alert("관리자권한이 없습니다.");</script><%}%>
<style type="text/css">
body {
   margin: 0;
   padding: 0;
}

#c {
	text-align: center;
}
#container {overflow: visible; min-height: 937px;}

#notice {
   width: 100%;
    border-collapse: collapse;
    line-height: 24px;
    padding-right: 82px;
}

#notice td, th{   
   padding: 10px;
}

#notice th {
   background: rgb(221, 221, 221);
   border-collapse: collapse;
}

table td, th{
   border-top:1px solid black;
    border-bottom:1px solid black;
    border-collapse: collapse;
}

#notice tr:hover {
   background-color: gray;
   border-left: 2px solid black;
}

#notice tr:first-child:hover {
   background-color: rgb(221, 221, 221);
}

#main {
   margin: 0 auto;
   height: 100%;
   width: 70%;
   position: relative;
   top: 40px;
}

#search {
   float: right;
   margin: 10px;
}

#content {
   width: 100%;
   /* height: calc(100% - 50px); */
   height: auto;
}


th, td {
   padding: 10px;
}

.page {
	position: relative;
	top: 40px;
}

.page span {
	padding: 0 6px;
    font-size: 15px;
    color: #7a7a7a;
    font-weight: 700;
}

.page span ,img{vertical-align: middle;}

.page #on {	color: #cf1f21;}

.page a {
	text-decoration: none;
	color: #7a7a7a;
}
#wirte {position: relative; top: 0px; left: 95%;}
#post {position: relative; left: 0; top: -6px; width: 500px;}
.listNum {position: relative; top: 55px; left: 140px;}
.page{position: relative; top:-50px;}
.copyright {width: 100%;height: 50px;background-color: gray; position: absolute; top: 100%; transform: translate(0,-100%);}
</style>
</head>
<body>
   <div id="container">
   <%@ include file="./menu.jsp" %>
   <div class="banner">
		<img alt="Banner" src="./img/notice.jpg">
    </div>
         <div class="main">
     		 <div class="location"> 
	      		<font style="font-weight: 900; font-size: 30px;">
      		<font style="text-decoration: overline;">NO</font>TICE</font> 
	     	</div>
             <div class="listNum">
      	<c:if test="${on ne 1 }">
				<a href="./notice?limit=10&on=1&page=${page }" ><img src="./img/10off.jpg"></a>
			</c:if>
			<c:if test="${on eq 1 }">
				<a href="./notice?limit=10&on=1&page=${page }" ><img src="./img/10on.jpg"></a>
			</c:if>
			
			<c:if test="${on ne 2 }">
				<a href="./notice?limit=20&on=2&page=${page }" ><img src="./img/20off.jpg"></a>
			</c:if>
			<c:if test="${on eq 2 }">
				<a href="./notice?limit=20&on=2&page=${page }"><img src="./img/20on.jpg"></a>
			</c:if>
			
			<c:if test="${on ne 3 }">
				<a href="./notice?limit=1000&on=3&page=${page }"><img src="./img/alloff.jpg"></a>
			</c:if>
			<c:if test="${on eq 3 }">
				<a href="./notice?limit=1000&on=3&page=${page }"><img src="./img/allon.jpg"></a>
			</c:if>
     	 </div>
            
               <div id="post">
                  <form method="post" action="./notice">
                     <select name="opt">
                        <!-- <option value="title_content">제목+내용</option> -->
                        <option value="title"> 제목</option>
                        <option value="content">내용</option>                        
                     </select>
                     <input type="text" name="condition" placeholder="단어를 입력하세요.">
                     <button type="submit">검색</button>
                  </form>
               </div>

            <div id="notice">
            <table>
               <tr>
                  <th style="width: 1%" id="c">번호</th>
                  <th style="width: 17%" id="c"> 제 목</th>
                  <th style="width: 5%" id="c">글쓴이</th>
                  <th style="width: 10%" id="c">날짜</th>
                  <th style="width: 1%" id="c">조회수</th>
               </tr>


               <c:forEach items="${list }" var="row">
                  <tr onclick="location.href='./noticedetail?bno=${row.notice_no}'">
                     <%-- <tr onclick="location.href='./detail.jsp?boardtype=${boardtype }&bno=${row.board_no }'"> --%>
                     <td id="c">${row.notice_no }</td>
                     <td>${row.notice_title }</td>
                     <td id="c">${row.member_name }</td>
                     <td id="c">${row.notice_date }</td>
                     <td id="c">${row.notice_view }</td>
                  </tr>
               </c:forEach>
            </table>
            <br><c:if test="${grade eq 1 }">	
             <button id="wirte" onclick="location.href='./noticewrite'">글쓰기</button>   
   			 </c:if>
            <div class="page" style="text-align: center;">
         <fmt:parseNumber integerOnly="true" var="totalPage" value="${total / 10 }"/>      
         <c:if test="${(total % 10 ge 1 )}">
            <c:set var="totalPage" value="${totalPage + 1 }"/>
         </c:if>
         <c:if test="${page ge 1 }">
            <a href="./notice?page=${page}"><img src="./img/first.jpg"></a>
         </c:if>
         
         <c:if test="${page ge 1 }">
            <a href="gyeonggi?10=${10}"><img src="./img/left.jpg"></a>
         </c:if>
         <c:forEach begin="1" end="${totalPage }" var="i">
         <span>
            <a href="./notice?page=${i}"  id="${page eq i ? 'on' : null }">${i }</a>
            <c:if test="${totalPage gt i}">ㅣ</c:if>
         </span>
         </c:forEach>
         
         <c:if test="${page le totalPage }">
            <a href="notice?10=${10}&page=${page+1 }&on=${on }&view=${view }"><img src="./img/right.jpg"></a>
         </c:if>
         
         <c:if test="${page le totalPage }">
            <a href="notice?10=${10}&page=${totalPage }&on=${on }&view=${view }"><img src="./img/last.jpg"></a>
         </c:if>
      </div>
     
                     
            </div>
         </div>
      <div class="copyright" style="text-align: center; font-size: 20px">
    	Copyright(C) 2021.놀러가고싶조.All rights reserved.
	</div>
      </div>
</body>
</html>