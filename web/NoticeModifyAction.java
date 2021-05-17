package com.poseidon.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.NoticeDAO;
import com.poseidon.dto.NoticeDTO;

@WebServlet("/noticemodifyAction")
public class NoticeModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NoticeModifyAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int grade = 0;
		int bno = 0;
		if(session.getAttribute("member_grade") != null) {
			grade = (int)session.getAttribute("member_grade");
		}
		System.out.println(grade);
		if(request.getParameter("bno") != null) {
			bno = Integer.parseInt(request.getParameter("bno"));
		}
		if(bno != 0	&& request.getParameter("title") != null && request.getParameter("content") != null && grade == 1) {
			String title = request.getParameter("title");
			title = title.replaceAll("<", "&lt");
			title = title.replaceAll(">", "&gt");
			String content = request.getParameter("content");
			
			NoticeDTO dto = new NoticeDTO();
			dto.setNotice_no(bno);
			dto.setNotice_title(title);
			dto.setNotice_content(content);
			
			NoticeDAO dao = new NoticeDAO();
			dao.modify(dto);
			 
			//esponse.sendRedirect("./notice");
			response.sendRedirect("./noticedetail?bno="+bno);
			
		} else {
				response.sendRedirect("./noticedetail?bno="+bno+"&noadmin");
		}
	}

}
