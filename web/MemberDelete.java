package com.poseidon.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.LoginDAO;
import com.poseidon.dao.NoticeDAO;

@WebServlet("/memberdelete")
public class MemberDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int grade = 0;
		int member_no = 0;
		int mno =0;
		if(session.getAttribute("member_grade") != null) grade = (int)session.getAttribute("member_grade");
		if(session.getAttribute("member_no") != null) member_no = (int)session.getAttribute("member_no");
		if(request.getParameter("mno") != null) mno = Integer.parseInt(request.getParameter("mno"));
		if(mno != 0 && grade == 1 && member_no == 10) {
			LoginDAO dao = new LoginDAO();
			dao.memberDel(mno);
			response.sendRedirect("./admin");
		} else {
			response.sendRedirect("./admin?noadmin");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
