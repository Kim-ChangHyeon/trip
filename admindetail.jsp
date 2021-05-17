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
<style type="text/css">
body {
   margin: 0;
   padding: 0;
}

#c {
	text-align: center;
}
#container {overflow: visible;}

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
   top: 120px;
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
.copyright {width: 100%;height: 50px;background-color: gray;}
.admin {width: 100%; min-width: 1262px;}
.main {top:120px;}
#notice h2{float: left; margin-top: 10px;}
</style>
<script type="text/javascript">
function del(num,mno){
	var check = confirm("해당 게시글을 삭제하시겠습니까?")
	
	if(check == true){
		alert(num + "번 게시글을 삭제합니다.");
		location.href="./admintripdelete?bno="+num+"&mno="+mno;
	} else{
		alert("삭제를 취소하였습니다.")
		return false;
	}
}
function cdel(num,mno){
	var check = confirm("해당 댓글을 삭제하시겠습니까?")
	
	if(check == true){
		alert(num + "번 댓글을 삭제합니다.");
		location.href="./admincommentdelete?cno="+num+"&mno="+mno;
	} else{
		alert("삭제를 취소하였습니다.")
		return false;
	}
}
</script>
</head>
<body>
   <div id="container">
   <%@ include file="./menu.jsp" %>
         <div class="main">
     		 <div class="location"> 
	      		<font style="font-weight: 900; font-size: 30px;">
      		<font style="text-decoration: overline;">AD</font>MIN</font> 
	     	</div>
            <div id="notice">
            <h2 style="margin-bottom: 30px;">${name }님이 쓴 여행후기 게시글입니다.</h2>
            <table class="admin">
               <tr>
                  <th style="width: 1%" id="c">번호</th>
                  <th style="width: 10%" id="c">제목</th>
                  <th style="width: 1%" id="c">이름</th>
                  <th style="width: 2%" id="c">날짜</th>
                  <th style="width: 1%" id="c">조회수</th>
                  <th style="width: 1%" id="c">삭제</th>
               </tr>


               <c:forEach items="${list }" var="row">
                  <tr>
                     <td id="c">${row.rno }</td>
                     <td id="c">${row.rtitle }</td>
                     <td id="c">${row.member_name }</td>
                     <td id="c">${row.rdate }</td>
                     <td id="c">${row.rviews }</td>
                     <td id="c"><input type="button" value="삭제" onclick="return del(${row.rno},${row.member_no })"></td>
                  </tr>
               </c:forEach>
            </table>
            
            <div id="notice">
            <h2 style="margin: 80px 0 30px;">${name }님이 쓴 여행후기 댓글입니다.</h2>
            <table class="admin">
               <tr>
                  <th style="width: 1%" id="c">글번호</th>
                  <th style="width: 1%" id="c">이름</th>
                  <th style="width: 10%" id="c">댓글본문</th>
                  <th style="width: 2%" id="c">날짜</th>
                  <th style="width: 1%" id="c">삭제</th>
               </tr>


               <c:forEach items="${clist }" var="row">
                  <tr>
                     <td id="c">${row.rno }</td>
                     <td id="c">${row.member_name }</td>
                     <td id="c">${row.rccontent }</td>
                     <td id="c">${row.rcdate }</td>
                     <td id="c"><input type="button" value="삭제" onclick="return cdel(${row.cno},${row.member_no })"></td>
                  </tr>
               </c:forEach>
            </table>
     
                     
            </div>
         </div>
      </div>
</body>
</html>