<%@page import="com.poseidon.dto.NoticeDTO"%>
<%@page import="com.poseidon.dao.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>놀러가고싶조</title>
<link href="https://fonts.googleapis.com/css2?family=Poor+Story&display=swap" rel="stylesheet">
<%if(request.getParameter("noadmin") != null){%><script type="text/javascript">alert("관리자권한이 없습니다.");</script><%}%>
<script type="text/javascript">
function del(num){
	var check = confirm("게시물을 삭제하시겠습니까?")
	
	if(check == true){
		alert(num + "번 게시물을 삭제합니다.");
		location.href="noticedelete?bno="+num;
	} else{
		alert("삭제를 취소하였습니다.")
		return false;
	}
}
</script>
<style type="text/css">
#detail {
	height: 100%;
	width: 98%;
	background-color: white;
	max-height: 450px;
	border-collapse: collapse;
	font-family: Poor Story;
	margin: 10px 10px 0;
}

#main {
	margin: 0 auto;
	height: 100%;
	width: 70%;
	padding: 0 10px 40px;
	border: 1px solid #e2e2e2;
}
th{
	width: 100px;
}

td{
	width: 10%;
}

#a{
	font-size: 20px;

}

#btn{
	align: center;
   	width: 30%;
	margin-bottom: 70px;
	position: relative;
	left: 100%;
	transform: translate(-60%,0);
}
#view { height: 23px; position: relative; top: -2px; margin-left: 10px;}
#date {vertical-align: middle;height:17px; margin-right: 5px;position: relative;top: 2px; float: right;}
.banner {margin-left: 5px; margin-right: 5px; position: relative; top: 70px;}
.copyright {width: 100%;height: 50px;background-color: gray; position: relative; top: 200px;}
.banner img{width: 100%;height: 200px;}
.cont {width: 100%; min-height: 300px; height: 100%;}
.location {position: relative; top: 110px; left: 286px;}
</style>
</head>
<body>
	<div id="container">
	<%@ include file="./menu.jsp" %>
	
	<div class="banner">
		<img alt="Banner" src="./img/notice.jpg">
    </div>
    <div class="location"> 
      		<font style="font-weight: 900; font-size: 30px;">
      		<font style="text-decoration: overline;">NO</font>TICE</font> 
    	</div>
				<div style="position: relative; top: 150px;">>
			<div id="main">
				<table id="detail">	
					<tr>
						<td style="font-size: 34px; font-weight: bold;" colspan="3">${detail.notice_title }</td>
					</tr>
					
					<tr>
						<th colspan="3" style="padding-top: 10px;">${detail.member_name } 
						<img id="view" alt="view" src="./img/view.png" style="vertical-align: middle;">${detail.notice_view }회
						<!-- <td id="a" style="width: 200px;text-align: right;"> -->
						<font style="float: right;">${detail.notice_date }</font><img id="date" alt="date" src="./img/date.png"></th>
					</tr>
				</table>
					<hr>
					<div class="cont" id="a">
						${detail.notice_content }
					</div>
				<hr>
				<c:if test="${grade eq 1}">
				<div id="btn">
				<button onclick="location.href='./noticemodify?bno=${detail.notice_no }'">수정하기</button>
				<button onclick="return del(${detail.notice_no })">삭제하기</button>
				<button onclick="location.href='./notice'">이전으로</button>
				</div>
				</c:if>
				<c:if test="${grade ne 1}">
				<div id="btn" style="transform: translate(-20%,0);">
				<button onclick="location.href='./notice'">이전으로</button>
				</div>
				</c:if>
			</div>
	</div>
		</div>
				</div>
				<div class="copyright">
    	copyright
</body>
</html>