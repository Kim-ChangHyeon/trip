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
<link rel="stylesheet" href="./css/popup.css">
<style>
	* {margin:0;padding:0;}
	.section input[id*="slide"] {display:none;}
	.section .slidewrap {max-width:1200px;margin:0 auto;}
	.section .slidelist {white-space:nowrap;font-size:0;overflow:hidden;position:relative;}
	.section .slidelist > li {display:inline-block;vertical-align:middle;width:100%;transition:all .5s;}
	.section .slidelist > li > a {display:block;position:relative;}
	.section .slidelist > li > a img {width:100%; height: 600px;}
	.section .slidelist label {position:absolute;z-index:10;top:50%;transform:translateY(-50%);padding:50px;cursor:pointer;}
	.section .slidelist .textbox {position:absolute;z-index:1;top:50%;left:50%;transform:translate(-50%,-50%);line-height:1.6;text-align:center;}
	.section .slidelist .textbox h3 {font-size:36px;color:#fff;;transform:translateY(30px);transition:all .5s;}
	.section .slidelist .textbox p {font-size:16px;color:#fff;opacity:0;transform:translateY(30px);transition:all .5s;}
	
	.section input[id="slide01"]:checked ~ .slidewrap .slidelist > li {transform:translateX(0%);}
	.section input[id="slide02"]:checked ~ .slidewrap .slidelist > li {transform:translateX(-100%);}
	.section input[id="slide03"]:checked ~ .slidewrap .slidelist > li {transform:translateX(-200%);}
	.section input[id="slide04"]:checked ~ .slidewrap .slidelist > li {transform:translateX(-300%);}
	.section input[id="slide05"]:checked ~ .slidewrap .slidelist > li {transform:translateX(-400%);}
	.section input[id="slide06"]:checked ~ .slidewrap .slidelist > li {transform:translateX(-500%);}
	.section input[id="slide07"]:checked ~ .slidewrap .slidelist > li {transform:translateX(-600%);}
	.section input[id="slide08"]:checked ~ .slidewrap .slidelist > li {transform:translateX(-700%);}
	.section input[id="slide09"]:checked ~ .slidewrap .slidelist > li {transform:translateX(-800%);}
	.section input[id="slide10"]:checked ~ .slidewrap .slidelist > li {transform:translateX(-900%);}

	.section input[id="slide01"]:checked ~ .slidewrap li:nth-child(1) .textbox h3 {opacity:1;transform:translateY(0);transition-delay:.2s;}
	.section input[id="slide01"]:checked ~ .slidewrap li:nth-child(1) .textbox p {opacity:1;transform:translateY(0);transition-delay:.4s;}
	.section input[id="slide02"]:checked ~ .slidewrap li:nth-child(2) .textbox h3 {opacity:1;transform:translateY(0);transition-delay:.2s;}
	.section input[id="slide02"]:checked ~ .slidewrap li:nth-child(2) .textbox p {opacity:1;transform:translateY(0);transition-delay:.4s;}
	.section input[id="slide03"]:checked ~ .slidewrap li:nth-child(3) .textbox h3 {opacity:1;transform:translateY(0);transition-delay:.2s;}
	.section input[id="slide03"]:checked ~ .slidewrap li:nth-child(3) .textbox p {opacity:1;transform:translateY(0);transition-delay:.4s;}
	.section input[id="slide04"]:checked ~ .slidewrap li:nth-child(4) .textbox h3 {opacity:1;transform:translateY(0);transition-delay:.2s;}
	.section input[id="slide04"]:checked ~ .slidewrap li:nth-child(4) .textbox p {opacity:1;transform:translateY(0);transition-delay:.4s;}
	.section input[id="slide05"]:checked ~ .slidewrap li:nth-child(5) .textbox h3 {opacity:1;transform:translateY(0);transition-delay:.2s;}
	.section input[id="slide05"]:checked ~ .slidewrap li:nth-child(5) .textbox p {opacity:1;transform:translateY(0);transition-delay:.4s;}
	.section input[id="slide06"]:checked ~ .slidewrap li:nth-child(6) .textbox h3 {opacity:1;transform:translateY(0);transition-delay:.2s;}
	.section input[id="slide06"]:checked ~ .slidewrap li:nth-child(6) .textbox p {opacity:1;transform:translateY(0);transition-delay:.4s;}
	.section input[id="slide07"]:checked ~ .slidewrap li:nth-child(7) .textbox h3 {opacity:1;transform:translateY(0);transition-delay:.2s;}
	.section input[id="slide07"]:checked ~ .slidewrap li:nth-child(7) .textbox p {opacity:1;transform:translateY(0);transition-delay:.4s;}
	.section input[id="slide08"]:checked ~ .slidewrap li:nth-child(8) .textbox h3 {opacity:1;transform:translateY(0);transition-delay:.2s;}
	.section input[id="slide08"]:checked ~ .slidewrap li:nth-child(8) .textbox p {opacity:1;transform:translateY(0);transition-delay:.4s;}
	.section input[id="slide09"]:checked ~ .slidewrap li:nth-child(9) .textbox h3 {opacity:1;transform:translateY(0);transition-delay:.2s;}
	.section input[id="slide09"]:checked ~ .slidewrap li:nth-child(9) .textbox p {opacity:1;transform:translateY(0);transition-delay:.4s;}
	.section input[id="slide10"]:checked ~ .slidewrap li:nth-child(10) .textbox h3 {opacity:1;transform:translateY(0);transition-delay:.2s;}
	.section input[id="slide10"]:checked ~ .slidewrap li:nth-child(10) .textbox p {opacity:1;transform:translateY(0);transition-delay:.4s;}

	.slide-control > div {display:none;}
	.section .left {left:30px;background:url('./img/left.png') center center / 100% no-repeat;}
	.section .right {right:30px;background:url('./img/right.png') center center / 100% no-repeat;}
	.section input[id="slide01"]:checked ~ .slidewrap .slide-control > div:nth-child(1) {display:block;}
	.section input[id="slide02"]:checked ~ .slidewrap .slide-control > div:nth-child(2) {display:block;}
	.section input[id="slide03"]:checked ~ .slidewrap .slide-control > div:nth-child(3) {display:block;}
	.section input[id="slide04"]:checked ~ .slidewrap .slide-control > div:nth-child(4) {display:block;}
	.section input[id="slide05"]:checked ~ .slidewrap .slide-control > div:nth-child(5) {display:block;}
	.section input[id="slide06"]:checked ~ .slidewrap .slide-control > div:nth-child(6) {display:block;}
	.section input[id="slide07"]:checked ~ .slidewrap .slide-control > div:nth-child(7) {display:block;}
	.section input[id="slide08"]:checked ~ .slidewrap .slide-control > div:nth-child(8) {display:block;}
	.section input[id="slide09"]:checked ~ .slidewrap .slide-control > div:nth-child(9) {display:block;}
	.section input[id="slide10"]:checked ~ .slidewrap .slide-control > div:nth-child(10) {display:block;}

	.slide-pagelist {text-align:center;padding:20px;position: absolute; top: 670px; left: 50%; transform: translate(-50%,0); width: 100%;}
	.slide-pagelist > li {display:inline-block;vertical-align:middle;}
	.slide-pagelist > li > label {display:block;padding:8px 30px;border-radius:30px;background:#ccc;margin:20px 10px;cursor:pointer;}
	.section input[id="slide01"]:checked ~ .slidewrap .slide-pagelist > li:nth-child(1) > label {background:#999;}
	.section input[id="slide02"]:checked ~ .slidewrap .slide-pagelist > li:nth-child(2) > label {background:#999;}
	.section input[id="slide03"]:checked ~ .slidewrap .slide-pagelist > li:nth-child(3) > label {background:#999;}
	.section input[id="slide04"]:checked ~ .slidewrap .slide-pagelist > li:nth-child(4) > label {background:#999;}
	.section input[id="slide05"]:checked ~ .slidewrap .slide-pagelist > li:nth-child(5) > label {background:#999;}
	.section input[id="slide06"]:checked ~ .slidewrap .slide-pagelist > li:nth-child(6) > label {background:#999;}
	.section input[id="slide07"]:checked ~ .slidewrap .slide-pagelist > li:nth-child(7) > label {background:#999;}
	.section input[id="slide08"]:checked ~ .slidewrap .slide-pagelist > li:nth-child(8) > label {background:#999;}
	.section input[id="slide09"]:checked ~ .slidewrap .slide-pagelist > li:nth-child(9) > label {background:#999;}
	.section input[id="slide10"]:checked ~ .slidewrap .slide-pagelist > li:nth-child(10) > label {background:#999;}
	</style>
</head>
<body>

	<%@ include file="./menu.jsp"%>
	<div id="popup">
  	<div>
  		<div id="close_box_left" onclick="closed()"></div>
  	
	  	<div class="popup_main">
	  	<c:if test="${grade eq 1 }"><img id="delete" src="./img/delete.png" onclick="return del(${detail.location_no})"></c:if>
	  		<h1 id="title">${detail.location_title }</h1>
	  		<hr>
	  		
	  		<div class="popup_addr">
	  		<span>지역 :  ${detail.location_location }</span>
	  		</div>
	  		
	  		<div>
	  			<div class="section">
	<input type="radio" name="slide" id="slide01" checked>
	<input type="radio" name="slide" id="slide02">
	<input type="radio" name="slide" id="slide03">
	<input type="radio" name="slide" id="slide04">
	<input type="radio" name="slide" id="slide05">
	<input type="radio" name="slide" id="slide06">
	<input type="radio" name="slide" id="slide07">
	<input type="radio" name="slide" id="slide08">
	<input type="radio" name="slide" id="slide09">
	<input type="radio" name="slide" id="slide10">
	<div class="slidewrap">
		
		<ul class="slidelist">
			<!-- 슬라이드 영역 -->
			<li class="slideitem">
				<a>
					<img src="./upload/${detail.location_file1 }">
				</a>
			</li>
			<c:if test="${detail.location_file2 ne null}">
			<li class="slideitem">
				<a>
					<img src="./upload/${detail.location_file2 }">
				</a>
			</li>
			</c:if>
			<c:if test="${detail.location_file3 ne null}">
			<li class="slideitem">
				<a>
					<img src="./upload/${detail.location_file3 }">
				</a>
			</li>
			</c:if>
			<c:if test="${detail.location_file4 ne null}">
			<li class="slideitem">
				<a>
					<img src="./upload/${detail.location_file4 }">
				</a>
			</li>
			</c:if>
			<c:if test="${detail.location_file5 ne null}">
			<li class="slideitem">
				<a>
					<img src="./upload/${detail.location_file5 }">
				</a>
			</li>
			</c:if>
			<c:if test="${detail.location_file6 ne null}">
			<li class="slideitem">
				<a>
					<img src="./upload/${detail.location_file6 }">
				</a>
			</li>
			</c:if>
			<c:if test="${detail.location_file7 ne null}">
			<li class="slideitem">
				<a>
					<img src="./upload/${detail.location_file7 }">
				</a>
			</li>
			</c:if>
			<c:if test="${detail.location_file8 ne null}">
			<li class="slideitem">
				<a>
					<img src="./upload/${detail.location_file8 }">
				</a>
			</li>
			</c:if>
			<c:if test="${detail.location_file9 ne null}">
			<li class="slideitem">
				<a>
					<img src="./upload/${detail.location_file9 }">
				</a>
			</li>
			</c:if>
			<c:if test="${detail.location_file10 ne null}">
			<li class="slideitem">
				<a>
					<img src="./upload/${detail.location_file10 }">
				</a>
			</li>
			</c:if>
			<!-- 좌,우 슬라이드 버튼 -->
			<div class="slide-control">
				<div>
					<c:if test="${detail.location_file2 ne null }">
					<label for="slide${imgtotal }" class="left"></label>
					<label for="slide02" class="right"></label>
					</c:if>
					
					<c:if test="${detail.location_file2 eq null }">
					<label for="slide${imgtotal }" class="left"></label>
					<label for="slide01" class="right"></label>
					</c:if>
				</div>
				<c:if test="${detail.location_file2 ne null}">
				<div>
					<c:if test="${detail.location_file3 ne null }">
					<label for="slide01" class="left"></label>
					<label for="slide03" class="right"></label>
					</c:if>
					
					<c:if test="${detail.location_file3 eq null }">
					<label for="slide01" class="left"></label>
					<label for="slide01" class="right"></label>
					</c:if>
					
				</div>
				</c:if>
				<c:if test="${detail.location_file3 ne null}">
				<div>
				
					<c:if test="${detail.location_file4 ne null }">
					<label for="slide02" class="left"></label>
					<label for="slide04" class="right"></label>
					</c:if>
					
					<c:if test="${detail.location_file4 eq null }">
					<label for="slide02" class="left"></label>
					<label for="slide01" class="right"></label>
					</c:if>
					
				</div>
				</c:if>
				<c:if test="${detail.location_file4 ne null}">
				<div>
					<c:if test="${detail.location_file5 ne null }">
					<label for="slide03" class="left"></label>
					<label for="slide05" class="right"></label>
					</c:if>
					
					<c:if test="${detail.location_file5 eq null }">
					<label for="slide03" class="left"></label>
					<label for="slide01" class="right"></label>
					</c:if>
					
				</div>
				</c:if>
				<c:if test="${detail.location_file5 ne null}">
				<div>
					<c:if test="${detail.location_file6 ne null }">
					<label for="slide04" class="left"></label>
					<label for="slide06" class="right"></label>
					</c:if>
					
					<c:if test="${detail.location_file6 eq null }">
					<label for="slide04" class="left"></label>
					<label for="slide01" class="right"></label>
					</c:if>
					
				</div>
				</c:if>
				<c:if test="${detail.location_file6 ne null}">
				<div>
					<c:if test="${detail.location_file7 ne null }">
					<label for="slide05" class="left"></label>
					<label for="slide07" class="right"></label>
					</c:if>
					
					<c:if test="${detail.location_file7 eq null }">
					<label for="slide05" class="left"></label>
					<label for="slide01" class="right"></label>
					</c:if>
					
				</div>
				</c:if>
				<c:if test="${detail.location_file7 ne null}">
				<div>
					<c:if test="${detail.location_file8 ne null }">
					<label for="slide06" class="left"></label>
					<label for="slide08" class="right"></label>
					</c:if>
					
					<c:if test="${detail.location_file8 eq null }">
					<label for="slide06" class="left"></label>
					<label for="slide01" class="right"></label>
					</c:if>
					
				</div>
				</c:if>
				<c:if test="${detail.location_file8 ne null}">
				<div>
					<c:if test="${detail.location_file9 ne null }">
					<label for="slide07" class="left"></label>
					<label for="slide09" class="right"></label>
					</c:if>
					
					<c:if test="${detail.location_file9 eq null }">
					<label for="slide07" class="left"></label>
					<label for="slide01" class="right"></label>
					</c:if>
					
				</div>
				</c:if>
				<c:if test="${detail.location_file9 ne null}">
				<div>
					<c:if test="${detail.location_file10 ne null }">
					<label for="slide08" class="left"></label>
					<label for="slide10" class="right"></label>
					</c:if>
					
					<c:if test="${detail.location_file10 eq null }">
					<label for="slide08" class="left"></label>
					<label for="slide01" class="right"></label>
					</c:if>
					
				</div>
				</c:if>
				<c:if test="${detail.location_file10 ne null}">
				<div>	
					<label for="slide09" class="left"></label>
					<label for="slide01" class="right"></label>	
				</div>
				</c:if>
			</div>

			</ul>
			<!-- 페이징 -->
			<ul class="slide-pagelist">
				<li><label for="slide01"></label></li>
				<c:if test="${detail.location_file2 ne null}"><li><label for="slide02"></label></li></c:if>
				<c:if test="${detail.location_file3 ne null}"><li><label for="slide03"></label></li></c:if>
				<c:if test="${detail.location_file4 ne null}"><li><label for="slide04"></label></li></c:if>
				<c:if test="${detail.location_file5 ne null}"><li><label for="slide05"></label></li></c:if>
				<c:if test="${detail.location_file6 ne null}"><li><label for="slide06"></label></li></c:if>
				<c:if test="${detail.location_file7 ne null}"><li><label for="slide07"></label></li></c:if>
				<c:if test="${detail.location_file8 ne null}"><li><label for="slide08"></label></li></c:if>
				<c:if test="${detail.location_file9 ne null}"><li><label for="slide09"></label></li></c:if>
				<c:if test="${detail.location_file10 ne null}"><li><label for="slide10"></label></li></c:if>
			</ul>
		</div>
	
		
		</div>
		  </div>
	  		
	  		<div class="popup_content">
	  		${detail.location_content }
  			</div>

	  		
	  		<div class="copyright">copyright</div>
  		</div>
	 </div>
  </div>
 <script>
  function closed() {
  		location.href="./location?location=${location}&limit=${limit}&page=${page }&on=${on }&view=${view }";
  }
  function del(num){
 	var check = confirm("게시물을 삭제하시겠습니까?");
 	if(check == true){
 		alert(num + "번 게시물을 삭제합니다.");
  		location.href="locationdelete?location=${location}&bno="+num;
  	} else{
  		alert("삭제를 취소하였습니다.");
  		return false;
  	}
  }
  </script>
</body>
</html>