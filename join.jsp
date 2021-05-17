<%@page import="com.poseidon.dao.LoginDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>놀러가고싶조</title>
</head>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
    function check(){
    	var name = document.getElementById("name");
    	var id = document.getElementById("id");
    	var pw = document.getElementById("pw");
    	var pw2 = document.getElementById("pw2");
    	var email1 = document.getElementById("email1");
    	var email2 = document.getElementById("email2");
    	var tel1 = document.getElementById("tel1");
    	var tel2 = document.getElementById("tel2");
    	var tel3 = document.getElementById("tel3");
    	if(id.value==""){
    		alert("아이디를 입력하시오.")
    		id.focus();
    		return false;
    	}
    	if(pw.value==""){
    		alert("암호를 입력하시오.")
    		pw.focus();
    		return false;
    	}
    	if(pw2.value==""){
    		alert("2차 암호를 입력하시오.")
    		pw2.focus();
    		return false;
    	}
    	if(pw.value!=pw2.value){
    		alert("암호가 서로 다릅니다.")
    		pw2.focus();
    		return false;
    	}
    	if(name.value==""){
    		alert("이름을 입력하시오.")
    		name.focus();
    		return false;
    	}
    	if(email.value==""){
    		alert("이메일을 입력하시오.")
    		email.focus();
    		return false;
    	}
 		if(tel1.value==""){
 		alert("전화번호를 입력해주세요.");
 		tel1.focus();
 		return false;
 		}
 		if(tel2.value==""){
 		alert("전화번호를 입력해주세요.");
 		tel2.focus();
 		return false;
 		}
 		if(tel3.value==""){
 		alert("전화번호를 입력해주세요.");
 		tel3.focus();
 		return false;
 		}
    }
    function checkID(){
		//alert("!");
		var id = $("#id").val();
		//alert(id);
		if(id == "" || id.length < 4){
			alert("ID는 빈칸이 아니거나 4자보다 길어야 합니다.");
			return false;
		}
		$.ajax({
			type: 'POST',
			data : "id=" + id,
			dataType : 'html',
			url : './idCheck.jsp',
			success : function(rData, textStatus, xhr){
				var checkresult = rData;
				//alert(rData +  textStatus +  xhr);
				if(checkresult == 0){
					alert("등록 가능합니다. 계속 진행하세요.");
					$("#btn").prop('disabled', false);
					
					$("#checkID").text("등록 가능합니다. 계속 진행하세요.");
					
				} else {
					alert("이미 등록되었습니다. 다른 ID를 입력하세요.");
					$("#btn").prop('disabled', true);
					
					$("#checkID").text("이미 등록되었습니다. 다른 ID를 입력하세요.");
				}
			},
			error : function(xhr, status, e){
				alert("에러가 발생했습니다.");
				$("#checkID").css('color','red');
				$("#checkID").text("에러가 발생했습니다.");
			}
		});
	}
</script>
<style type="text/css"></style>
<body>
	<%@ include file="./menu.jsp" %>
		<div class="container" style="position: relative; top: 70px;">
			<h1 class="text-muted text-center mt-3 mb-3">회원 가입</h1>
			<form action="./join" method="post" >
				<table class="table">
					<colgroup>
						<col width="25%">
						<col width="75%">
					</colgroup>
					<tbody>
						<tr>
							<th class="text-right">아이디</th>
							<td>
							<div class="col-xs-3">
								<input type="text" class="form-control" id="id" name="id" maxlength="20" placeholder="아이디를 입력하세요.">
							</div>	
							<button type="button" class="btn btn-primary" onclick="checkID()">중복확인</button>
							</td>
						</tr>
						<tr>
							<th class="text-right">비밀번호</th>
							<td>
							<div class="col-xs-3">
								<input type="password" class="form-control" id="pw" name="pw" maxlength="20" placeholder="비밀번호를 입력하세요.">
							</div>	
							</td>
						</tr>
						<tr>
							<th class="text-right">비밀번호 확인</th>
							<td>
							<div class="col-xs-3">
								<input type="password" class="form-control" id="pw2" name="pw2" maxlength="20">
							</div>
							</td>
						</tr>
						<tr>
							<th class="text-right">이름</th>
							<td>
							<div class="col-xs-3">
								<input type="text" class="form-control" id="name" name="name" maxlength="6">
							</div>
							</td>
						</tr>
						<tr>
							<th class="text-right">생년월일</th>
							<td>
							<div class="col-xs-3">
								<input type="date" class="form-control" id="birth" name="birth" min="1900-01-01" max="2021-02-16" placeholder="생년월일">
							</div>
							</td>
						</tr>
						<tr>
							<th class="text-right">성별</th>
							<td class="form-inline">
								<input type="radio" class="form-control" id="gender" name="gender" value="남자">
								남자
								<input type="radio" class="form-control" id="gender" name="gender" value="여자">
								여자
							</td>
						</tr>
						<tr>
							<th class="text-right">E-mail</th>
							<td class="form-inline">
							
								<input type="text" class="form-control" id="email1" name="email1" maxlength="20">
								@ <input type="text" class="form-control" id="email2" name="email2" maxlength="20">
							
							</td>
						</tr>
						<tr>
							<th class="text-right">핸드폰</th>
							<td class="form-inline">
							
								<input type="text" class="form-control" id="tel1" name="tel1" size="3" maxlength="3">
								- <input type="text" class="form-control" id="tel2" name="tel2" size="4" maxlength="4">
								-<input type="text" class="form-control" id="tel3" name="tel3" size="4" maxlength="4">
							
							</td>
						</tr>
						<tr>
							<th class="text-right">주소</th>
							<td class="form-inline">
							<input class="form-control" type="text" id="sample4_postcode" placeholder="우편번호" name="addr1">
							<input class="form-control" type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
							<input class="form-control" type="text" id="sample4_roadAddress" placeholder="도로명주소" name="addr2">
							<input class="form-control" type="text" id="sample4_jibunAddress" placeholder="지번주소" name="addr3">
							<span id="guide" style="color:#999;display:none"></span>
							<input class="form-control" type="text" id="sample4_detailAddress" placeholder="상세주소" name="addr4">
							<input class="form-control" type="text" id="sample4_extraAddress" placeholder="참고항목">
							</td>
						</tr>
					</tbody>
				</table>
			<div class="text-center">
			<button type="submit" class="btn btn-primary" onclick="return check()" id="btn">회원 가입</button>
			</div>
			</form>
		</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>