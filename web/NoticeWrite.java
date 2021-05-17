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

@WebServlet("/noticewrite")
public class NoticeWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NoticeWrite() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int grade = 0;
		if(session.getAttribute("member_grade") != null) grade = (int)session.getAttribute("member_grade");	
		request.setAttribute("grade", grade);	
		RequestDispatcher rd = request.getRequestDispatcher("./noticewrite.jsp");
		rd.forward(request, response);			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//System.out.println(request.getParameter("title"));
		//System.out.println(request.getParameter("content"));
		HttpSession session = request.getSession();
		int grade = 0;
		int member_no = 0;
		if(session.getAttribute("member_grade") != null) {
			grade = (int)session.getAttribute("member_grade");
		}
		if(session.getAttribute("member_no") != null) {
			member_no = (int)session.getAttribute("member_no");
		}
		if(request.getParameter("title") != null && request.getParameter("content") != null && grade == 1 && member_no == 10)  {
			String title = request.getParameter("title");
			title = title.replaceAll("<", "&lt");
			title = title.replaceAll(">", "&gt");
			
			String content = request.getParameter("content");

			NoticeDTO dto = new NoticeDTO();
			dto.setNotice_title(title);
			dto.setNotice_content(content);
			dto.setMember_no(member_no);
			NoticeDAO dao = new NoticeDAO();
			dao.write(dto);
			//RequestDispatcher rd = request.getRequestDispatcher("./notice");
			//rd.forward(request, response);
			response.sendRedirect("./notice");
		}  else {
			response.sendRedirect("./notice?noadmin");
		}
	}

}
