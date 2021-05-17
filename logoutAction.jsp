<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>놀러가고싶조</title>
</head>
<body>

<%
session.invalidate();
response.sendRedirect("./main");
%>
</body>
</html>