package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.poseidon.dao.LoginDAO;
import com.poseidon.dto.LoginDTO;


@WebServlet("/join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Join() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("./join.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		LoginDTO dto = new LoginDTO();
		dto.setMember_email1(request.getParameter("email1"));
		dto.setMember_email2(request.getParameter("email2"));
		dto.setMember_name(request.getParameter("name"));
		dto.setMember_pw(request.getParameter("pw"));
		dto.setMember_id(request.getParameter("id"));
		dto.setMember_addr1(request.getParameter("addr1"));
		dto.setMember_addr2(request.getParameter("addr2"));
		dto.setMember_addr3(request.getParameter("addr3"));
		dto.setMember_addr4(request.getParameter("addr4"));
		dto.setMember_birth((request.getParameter("birth")));
		dto.setMember_gender(request.getParameter("gender"));
		dto.setMember_tel1((request.getParameter("tel1")));
		dto.setMember_tel2((request.getParameter("tel2")));
		dto.setMember_tel3((request.getParameter("tel3")));
	

		LoginDAO dao = new LoginDAO();
		System.out.println(dto.getMember_birth());
		dao.join(dto);
		response.sendRedirect("./login.jsp?joinsuccess");

		
	}

}
