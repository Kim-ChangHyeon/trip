package com.poseidon.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.LoginDAO;

@WebServlet("/gradechange")
public class GradeChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GradeChange() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		int mno = 0;
		int grade = 0;
		HttpSession session = request.getSession();
		if(session.getAttribute("member_grade") != null)
		if(request.getParameter("mno") != null) mno = Integer.parseInt(request.getParameter("mno"));
		if(request.getParameter("gradeChange") != null) grade = Integer.parseInt(request.getParameter("gradeChange"));
		if((int)session.getAttribute("member_grade") == 1&& (int)session.getAttribute("member_no") == 10) {
			LoginDAO dao = new LoginDAO();
			dao.gradeChange(mno,grade);	
			response.sendRedirect("./admin?gradesucces");
		} else {
			response.sendRedirect("./admin?gradeerror");
		}
	}

}
