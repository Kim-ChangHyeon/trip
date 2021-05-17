<%@page import="com.poseidon.dto.NoticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>놀러가고싶조</title>
<link href="https://fonts.googleapis.com/css2?family=Poor+Story&display=swap" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
$(document).ready(function() {
   $('#summernote').summernote({
      lang : 'ko-KR',
      height : 300,
      fontNames: ['Poor Story',  'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', ],
      fontNamesIgnoreCheck: ['Poor Story']
   });
});
</script>
<!-- css 파일 확인해야함!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
<style type="text/css">
#container {position: relative;top: 95px;}
#write textarea {
	height: 500px;
	width: 100%;
	background-color: #c0c0c0;
	border: 0;
	margin : 0;
	padding: 0;
	margin: 10px 0px;
	padding:10px;
	box-sizing:border-box;
}
#write{
	margin:0 auto;
	width: 900px;
	height: 600px;
	position: relative;
	top:0;
}
#write input[type=text]{
	height: 30px;
	width: 100%;
	border: 1px solid black;
	margin : 0;
	padding: 0;
	padding-left:10px;
	box-sizing:border-box;
	margin-bottom: 5px;
}
</style>
</head>
<body>
	<%-- <div id="container">
	<%@ include file="./menu.jsp" %>
		<link href="./summernote/summernote.min.css" rel="stylesheet">
	<script src="./summernote/summernote.min.js"></script>
		<div id="content">
			<div id="write">
				<hr>
				<form action="./noticemodifyAction" method="post">
					<input type="text" name="title" value="${modify_notice_title }" style="width: 50%"><br>
					<textarea name="content" id="summernote">${modify_notice_content }</textarea><br> 
					<input type="hidden" name="bno" value="${modify_notice_no }">
					<button type="submit">수정하기</button>
				</form>
			</div>
		</div>
	</div> --%>
	
		<div id="container">
	<%@ include file="./menu.jsp" %>
		<link href="./summernote/summernote.min.css" rel="stylesheet">
	<script src="./summernote/summernote.min.js"></script>
		<div id="content">
			<div id="main">
				<h1 style="text-align: center;">공지 수정하기</h1>
				<div id="write">
					<form action="./noticemodifyAction" method="post">
						<input type="text" id="title" name="title" value="${modify.notice_title }"><br>
						<textarea name="content" id="summernote">${modify.notice_content }</textarea>
						<input type="hidden" value="${modify.notice_no }" name="bno">
						<button type="submit">수정하기</button>
					</form>
				</div>
				<script type="text/javascript">
					$(document).ready(function() {$('#summernote').summernote({height : 500});});
				</script>
			</div>	
		</div>
	</div>
	<script type="text/javascript">$(document).ready(function() {$('#summernote').summernote({height : 500});});</script>
	
</body>
</html>