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
<link href="./css/tripdetail.css">
<%if(request.getParameter("disagree") != null){%><script type="text/javascript">alert("사용자가 쓴 글이 아닙니다.");</script><%}%>
<%if(request.getParameter("discomment") != null){%><script type="text/javascript">alert("사용자가 쓴 댓글이 아닙니다.");</script><%}%>
<%if(request.getParameter("dislogin") != null){%><script type="text/javascript">alert("로그인을 해야 합니다.");</script><%}%>
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
	margin-bottom: 50px;
	position: relative;
	left: 100%;
	transform: translate(-60%,0);
}
#view { height: 23px; position: relative; top: -2px; margin-left: 10px;}
#date {vertical-align: middle;height:17px; margin-right: 5px;position: relative;top: 2px; float: right;}
.banner {margin-left: 5px; margin-right: 5px; position: relative; top: 70px;}
.copyright {width: 100%;height: 50px;background-color: gray; position: relative; top: 200px;}
#input {width: 100%; height: 80px; font-size: 15px;}
.banner img{width: 100%;height: 200px;}
.cont {width: 100%; min-height: 300px; height: 100%; padding-left: 20px;}
.wdate {position: relative; top: -2px;  font-size: 10px;}
.name {font-size: 25px;}
.contentarea {width: 80%;}
.commentList {border-bottom: 1px solid #e2e2e2; margin: 0 auto; width: 96%;}
.contarea {width: 96%; border: none; border-bottom: 1px solid gray; padding-bottom: 3px; transition: 0.5s; margin-top: 40px; margin-left: 25px;}
.contarea:focus {border-bottom: 2px solid black; }
#cobtn {vertical-align: middle;border-radius: 1px;border: 1px solid white;display: inline-block;width: 72px;height: 39px;background-color: white;font-size: 15px;font-weight: 700;margin-top: 10px;position: relative;left: 93%; margin-bottom: 10px;}
.comments {width: 96%;margin: 0 auto; position: relative; left: 0; border: 2px solid gray; margin-top: 50px; border-radius: 3px;}
#mname {position:relative; left: 20px; top: 15px; font-family: 'Yeon Sung', cursive; font-size: 20px; }
#commentarea h2 {margin-left: 10px;}
.location {position: relative; top: 110px; left: 286px;}
</style>
</head>
<body>
	<div id="container">
	<%@ include file="./menu.jsp" %>
	
	<div class="banner">
		<img alt="Banner" src="./img/review.jpg">
    </div>
    <div class="location"> 
      		<font style="font-weight: 900; font-size: 30px;">
      		<font style="text-decoration: overline;">RE</font>VIEW</font> 
    	</div>
				<div style="position: relative; top: 150px;">>
			<div id="main">
				<table id="detail">	
					<tr>
						<td style="font-size: 34px; font-weight: bold;" colspan="3">${detail.rtitle }</td>
					</tr>
					
					<tr>
						<th colspan="3" style="padding-top: 10px;">${detail.member_name } 
						<img id="view" alt="view" src="./img/view.png" style="vertical-align: middle;">${detail.rviews }회
						<!-- <td id="a" style="width: 200px;text-align: right;"> -->
						<font style="float: right;">${detail.rdate }</font><img id="date" alt="date" src="./img/date.png"></th>
					</tr>
				</table>
					<hr>
					<div class="cont" id="a">
					<%String url = request.getSession().getServletContext().getRealPath("/"); %>
					<c:if test="${detail.rfile1 ne null }"><img alt="1" src="./upload/${detail.rfile1 }"></c:if>
					<c:if test="${detail.rfile2 ne null }"><img alt="2" src="./upload/${detail.rfile2 }"></c:if>
					<c:if test="${detail.rfile3 ne null }"><img alt="3" src="./upload/${detail.rfile3 }"></c:if>
					<c:if test="${detail.rfile4 ne null }"><img alt="4" src="./upload/${detail.rfile4 }"></c:if>
					<c:if test="${detail.rfile5 ne null }"><img alt="5" src="./upload/${detail.rfile5 }"></c:if>
					
						${detail.rcontent }
					</div>
				<hr>
				<c:choose>
					<c:when test="${grade eq 1 }">
					<div id="btn">
						<button onclick="location.href='./tripmodify?bno=${detail.rno }'">수정하기</button>
						<button onclick="return del(${detail.rno })">삭제하기</button>
						<button onclick="location.href='./tripreview'">이전으로</button>
						</div>
					</c:when>
					<c:when test="${checkuser eq 1 }">
					<div id="btn">
						<button onclick="location.href='./tripmodify?bno=${detail.rno }'">수정하기</button>
						<button onclick="return del(${detail.rno })">삭제하기</button>
						<button onclick="location.href='./tripreview'">이전으로</button>
					</div>
					</c:when>
					<c:otherwise>
					<div id="btn" style="transform: translate(-20%,0); left: 96%; width: 100px;">
						<button onclick="location.href='./tripreview'">이전으로</button>
					</div>
					</c:otherwise>
				</c:choose>
				<div id="commentarea">
				<h2>댓글 <c:if test="${commentcount gt 0}">${commentcount }개</c:if></h2>
				<hr>
				<c:forEach var="co" items="${clist }">
				<div class="commentList">
					<span id="font" class="name" >${co.member_name }</span>
					<span class="wdate">(${co.rcdate })</span>
					<c:set  var="rccontent" value = "${co.rccontent }"/>
					<c:set var="rccontent" value="${fn:replace(rccontent, rn, '<br>') }"/>

					<span class="update">
						<a href="./commentmodify?bno=${co.rno }&cno=${co.cno}" style="position: relative;left: 80%;">수정</a> &nbsp;
						<a href="./commentdelete?bno=${co.rno }&cno=${co.cno}" style="position: relative;left: 80%;">삭제</a>				
					</span>				
					<div class="contentarea" style="padding-top: 10px; padding-bottom: 20px;">
						${rccontent }
					</div>
					<c:if test="${comcno eq co.cno }">
					<div class="modify"> 
					<form action="./comments?bno=${detail.rno }&cno=${co.cno}" method="post"> 
					<input type="hidden" name="exe" value="2">
						<input class="contarea" type="text" name="rccontentmodify" value="${rccontent }"/>
						<input id="cobtn" type="submit" value="수정" <c:if test="${member_no eq 0 }">disabled="disabled"</c:if>>
					</form>
					</div>
					</c:if>
				</div>  			 
  			 </c:forEach>
  			 </div>
				<div class="comments">
			<div class="comment_form">
			<form action="./comments?bno=${detail.rno }" method="post"> 
				<input type="hidden" name="exe" value="1">
				<div id="mname">
				<c:if test="${member_no ne 0 }">${member_name }</c:if>
				</div>
				<input class="contarea" type="text" name="rccontent" placeholder="<c:choose><c:when test="${member_no eq 0}">로그인이 필요합니다.</c:when><c:when test="${member_no gt 0}">댓글을 작성하세요.</c:when></c:choose>"/>
				<input id="cobtn" type="submit" value="댓글" <c:if test="${member_no eq 0 }">disabled="disabled"</c:if>>

            </form>
			</div>
  			 </div>

			</div>
	</div>
		</div>
				</div>
				<div class="copyright">
    	copyright
</body>
</html>