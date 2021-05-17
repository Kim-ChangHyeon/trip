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
import com.poseidon.util.NumberCheck;

@WebServlet("/noticedetail")
public class NoticeDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeDetail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = 1;
		int grade = 0;
		HttpSession session = request.getSession();
		
		if(request.getParameter("bno") != null) {
			bno = NumberCheck.number(request.getParameter("bno"));
		}
		if(session.getAttribute("member_grade") != null) {
			grade = (int)session.getAttribute("member_grade");
		}
		
		NoticeDTO dto = new NoticeDTO();
		NoticeDAO dao = new NoticeDAO();
		
		dto = dao.detail(bno);
		request.setAttribute("detail", dto);
		request.setAttribute("grade", grade);
		RequestDispatcher rd = request.getRequestDispatcher("./noticedetail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
