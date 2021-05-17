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
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
#write #themeName {width: 95%}
#write{
	margin:0 auto;
	width: 900px;
	height: 600px;
	position: relative;
	top: 95px;
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
#write>#themeName {width: 95%}
</style>
</head>
<body>
	<div>
		<%@ include file="./menu.jsp" %>
		<link href="./summernote/summernote.min.css" rel="stylesheet">
<script src="./summernote/summernote.min.js"></script>
				<div id="write">
				<h1 style="text-align: center;">
				<c:if test="${theme eq 'all'}">ALL</c:if>
      			<c:if test="${theme eq 'food'}">맛집</c:if>
      			<c:if test="${theme eq 'tour'}">여행</c:if>
      			<c:if test="${theme eq 'play'}">체험</c:if> 
      			 글쓰기</h1>
					<form>
						<input type="text" id="title" name="title" placeholder="제목을 입력하세요"><br>
						<textarea name="content" id="summernote" class="conetent1"></textarea>
						<input type="hidden" name="theme" value="${param.theme }">
						테마 : <input type="text" id="themeName" name="themeName" placeholder="테마을 입력하세요.">
						파일 추가/제거 : <input type="button" value="파일추가" id="addFileBtn">
						<input type="button" value="파일제거" id="delFileBtn">
						<hr>
						<div id="fileArea">
							<input type="file" name="file1"> <br>
						</div>
						<hr>
						<input type="submit" value="글쓰기">
					</form>
					
				</div>
				</div>
				<script type="text/javascript">
					$(document).ready(function() {
						$('#summernote').summernote({
							height : 500
						});
					});
					
					var form = document.forms[0];
					var addFileBtn = document.getElementById("addFileBtn");
					var delFileBtn = document.getElementById("delFileBtn");
					var fileArea = document.getElementById("fileArea");
					var cnt = 1;
					
					addFileBtn.onclick = function() {
						if(cnt <= 9) {
							cnt++;
							var element = document.createElement("input");
							element.type="file";
							element.name="file"+cnt;
							
							fileArea.appendChild(element);
							fileArea.appendChild(document.createElement("br"));
						} else {
							alert("파일은 10개까지 추가 가능합니다.");
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
						var theme = document.getElementById("themeName");
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
						if(theme.value == null || theme.value.length < 2) {
							alert("테마를 입력하세요.");
							theme.focus();
							return false;
						}

						var inputs = fileArea.getElementsByTagName('input');
						for(var i=0; i<inputs.length; i++) {
							if(inputs[i].value == "") {
								alert('파일을 선택하세요.');
								inputs[i].focus();
								return;
							}
						}
						
						
						this.action = "./themewrite";
						this.method = "post";
						this.enctype = "multipart/form-data";
						this.submit();
						
					};
					
				</script>
</body>
</html>