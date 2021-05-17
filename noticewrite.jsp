<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>놀러가고싶조</title>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">

</script>
<style type="text/css">
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

#container {position: relative;top: 95px;}
</style>
</head>
<body>
	<div id="container">
	<%@ include file="./menu.jsp" %>
		<link href="./summernote/summernote.min.css" rel="stylesheet">
	<script src="./summernote/summernote.min.js"></script>
		<div id="content">
			<div id="main">
				<h1 style="text-align: center;">공지 글쓰기</h1>
				<div id="write">
					<form action="./noticewrite" method="post" onsubmit="return writeCheck()">
						<input type="text" id="title" name="title" placeholder="제목을 입력하세요"><br>
						<textarea name="content" id="summernote"></textarea>
						<button type="submit">글쓰기</button>
					</form>
				</div>
				<script type="text/javascript">
					$(document).ready(function() {$('#summernote').summernote({height : 500});});
				</script>
			</div>	
		</div>
	</div>
</body>
</html>