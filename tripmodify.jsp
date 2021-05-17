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
	
		<div id="container">
	<%@ include file="./menu.jsp" %>
		<link href="./summernote/summernote.min.css" rel="stylesheet">
	<script src="./summernote/summernote.min.js"></script>
		<div id="content">
			<div id="main">
				<h1 style="text-align: center;">여행후기 수정하기</h1>
				<div id="write">
					<form>
					<select name="rlocation">
						<option value="1">서울</option>
						<option value="2">인천</option>
						<option value="3">경기</option>
						<option value="4">강원</option>
						<option value="5">경상</option>
						<option value="6">충청</option>
						<option value="7">전라</option>
						<option value="8">제주</option>
					</select>
						<input type="text" id="title" name="rtitle" value="${modify.rtitle }"><br>
						<textarea name="rcontent" id="summernote">${modify.rcontent }</textarea>
						<input type="hidden" value="${modify.rno }" name="bno">
					파일 추가/제거 : <input type="button" value="파일추가" id="addFileBtn">
						<input type="button" value="파일제거" id="delFileBtn">
						<hr>
						<div id="fileArea">
							<input type="file" name="rfile1"> <br>
						</div>

						<button type="submit">수정하기</button>
					</form>
				</div>
				<script type="text/javascript">
					$(document).ready(function() {$('#summernote').summernote({height : 500});});
					var form = document.forms[0];
					var addFileBtn = document.getElementById("addFileBtn");
					var delFileBtn = document.getElementById("delFileBtn");
					var fileArea = document.getElementById("fileArea");
					var cnt = 1;
					
					addFileBtn.onclick = function() {
						if(cnt <= 5) {
							cnt++;
							var element = document.createElement("input");
							element.type="file";
							element.name="rfile"+cnt;
							
							fileArea.appendChild(element);
							fileArea.appendChild(document.createElement("br"));
						} else {
							alert("파일은 5개까지 추가 가능합니다.");
						}
					};
					
					delFileBtn.onclick = function() {
						if(cnt > 1) {
							cnt--;
							var inputs = fileArea.getElementsByTagName('input');
							var brArr = fileArea.getElementsByTagName('br');
							console.dir(inputs[cnt]);
							fileArea.removeChild(brArr[brArr.length-1]);
							fileArea.removeChild(inputs[inputs.length-1]);
						} else {
							alert("최소 1개의 파일을 업로드 바랍니다.");
						}
					};
					
					form.onsubmit = function() {
						event.preventDefault();
						var title = document.getElementById("title");
						var content = document.getElementById("summernote");
						if(title.value == null || title.value.length < 3) {
							alert("제목을 3글자 이상 입력하세요.");
							title.focus();
							return false;
						}
						if(content.value == null || content.value.length < 5) {
							alert("내용을 5글자 이상 입력하세요.");
							content.focus();
							return false;
						}

						var inputs = fileArea.getElementsByTagName('input');
						for(var i=1; i<inputs.length; i++) {
							if(inputs[i].value == "") {
								alert('파일을 선택하세요.');
								inputs[i].focus();
								return;
							}
						}
						this.action = "./tripmodifyaction";
						this.method = "post";
						this.enctype = "multipart/form-data";
						this.submit();
						
					};
				</script>
			</div>	
		</div>
	</div>
	<script type="text/javascript">$(document).ready(function() {$('#summernote').summernote({height : 500});});</script>
	
</body>
</html>