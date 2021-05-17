package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.NoticeDAO;
import com.poseidon.dto.NoticeDTO;

@WebServlet("/noticemodify")
public class NoticeModify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NoticeModify() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session = request.getSession();
		int grade = 0;
		int bno = 0;
		int member_no = 0;
		if(session.getAttribute("member_grade") != null) {
			grade = (int)session.getAttribute("member_grade");
		}
		if(session.getAttribute("member_no") != null) {
			member_no = (int)session.getAttribute("member_no");
		}
		if(request.getParameter("bno") != null) {
			bno = Integer.parseInt(request.getParameter("bno"));
			NoticeDAO dao = new NoticeDAO();
			NoticeDTO dto = dao.detail(bno);
			if (grade == 1 && member_no == 10) {
				RequestDispatcher rd = request.getRequestDispatcher("./noticemodify.jsp");
				request.setAttribute("modify", dto);
				request.setAttribute("grade", grade);
				rd.forward(request, response);
			} else {
				response.sendRedirect("/noticedetail?bno="+bno+"&noadmin");
			}
		} else {
			response.sendRedirect("/noticedetail?bno="+bno+"&noadmin");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
