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
.admin {width: 100%}
.main {top:120px;}
</style>
<script type="text/javascript">
function del(num){
	var check = confirm("해당 아이디를 삭제하시겠습니까?")
	
	if(check == true){
		alert(num + "번 아이디를 삭제합니다.");
		location.href="./memberdelete?mno="+num;
	} else{
		alert("삭제를 취소하였습니다.")
		return false;
	}
}
function change(num){
	var check = confirm("해당 등급을 변경하시겠습니까?")
	
	if(check == true){
		alert(num + "번 등급을 변경합니다.");
		location.href="./gradechange?mno="+num;
	} else{
		alert("변경을 취소하였습니다.");
		return false;
	}
}
</script>
<%if(request.getParameter("gradesucces") != null){%><script>alert("등급 변경이 완료되었습니다.");</script><%} %>
<%if(request.getParameter("gradeerror") != null){%><script>alert("관리자권한이 없습니다.");</script><%} %>
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
            <table class="admin">
               <tr>
                  <th style="width: 1%" id="c">번호</th>
                  <th style="width: 5%" id="c">아이디</th>
                  <th style="width: 7%" id="c">이름</th>
                  <th style="width: 10%" id="c">비밀번호</th>
                  <th style="width: 15%" id="c">이메일</th>
                  <th style="width: 4%" id="c">성별</th>
                  <th style="width: 25%" id="c">주소</th>
                  <th style="width: 10%" id="c">전화번호</th>
                  <th style="width: 10%" id="c">생일</th>
                  <th style="width: 2%" id="c">등급</th>
                  <th style="width: 2%" id="c">등급변경</th>
                  <th style="width: 2%" id="c">삭제</th>
               </tr>


               <c:forEach items="${member }" var="row">
               <c:if test="${row.member_no ne 10}">
                  <tr>
                     <td id="c" onclick="location.href='./admindetail?mno=${row.member_no}'">${row.member_no }</td>
                     <td onclick="location.href='./admindetail?mno=${row.member_no}'">${row.member_id }</td>
                     <td id="c" onclick="location.href='./admindetail?mno=${row.member_no}'">${row.member_name }</td>
                     <td id="c" onclick="location.href='./admindetail?mno=${row.member_no}'">${row.member_pw }</td>
                     <td id="c" onclick="location.href='./admindetail?mno=${row.member_no}'">${row.member_email }</td>
                     <td id="c" onclick="location.href='./admindetail?mno=${row.member_no}'">${row.member_gender }</td>
                     <td id="c" onclick="location.href='./admindetail?mno=${row.member_no}'">${row.member_addr }</td>
                     <td id="c" onclick="location.href='./admindetail?mno=${row.member_no}'">${row.member_tel }</td>
                     <td id="c" onclick="location.href='./admindetail?mno=${row.member_no}'">${row.member_birth }</td>
                     <td id="c" onclick="location.href='./admindetail?mno=${row.member_no}'">${row.member_grade }</td>
                     <form action="./gradechange?mno=${row.member_no }" method="post">
                     <td id="c"><select name="gradeChange"><option value="2" >2</option><option value="1" >1</option></select>
                     <input type="submit" value="변경" onclick="return change(${row.member_no})"></td>
                     </form>
                     <td id="c"><input type="button" value="삭제" onclick="return del(${row.member_no})"></td>
                  </tr>
               </c:if>
               </c:forEach>
            </table>
     
                     
            </div>
         </div>
      </div>
</body>
</html>