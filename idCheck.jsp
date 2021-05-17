<%@page import="com.poseidon.dao.LoginDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	LoginDAO dao = new LoginDAO();
int result = dao.idCheck(request.getParameter("id"));
//System.out.print(result);
%>
<%=result %>
